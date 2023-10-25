import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.*;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.proto.AchieveREInitiator;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.util.*;

public class DynamicRecipeAgent extends Agent {

    private enum ProcurementStatus {
        IDLE, START_NEGOTIATION, WAIT_FOR_RESULTS, EVAL_RESULTS, PROCESS_SUCCESSFUL_NEG, PROCESS_FAILED_NEG, IMPOSSIBLE_NEG
    }

    Map<String, NegotiationResult> activeNegotiations = new HashMap<>();
    private Stack<String> rawMaterialAvailable = new Stack<>();

    private boolean executing = false;

    private Map<String, Symbol> symbolTable;

    private Map<String, Double> recalculateRatio(Map<String, Double> ratio, List<Ingredient> infringingIngredients) {
        Map<String, Double> newRatio = new HashMap<>();
        for (Ingredient ingredient : infringingIngredients) {
            Range range = ingredient.getRange();
            switch (range.getType()) {
                case NUMERIC:
                    double currentValue = ratio.get(ingredient.getName());
                    if (currentValue - 10 > ((NumericRange) range).min && currentValue - 10 > 0) {
                        newRatio.put(ingredient.getName(), currentValue - 10);
                    } else {
                        return null;
                    }
                    break;
                //TODO the other ranges are odd to compute here for future versions see if they make any sense
            }
        }
        return newRatio;
    }

    private Behaviour covertFlowsToBehaviours(FlowDeclaration flow) {
        //TODO here we must handle the deviations when negotiation takes place
        switch (flow.getType()) {
            case ATOMIC:
                String resourceName = ((Atomic) flow).resource;
                ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
                msg.setContent(((Atomic) flow).function + " " + ((Atomic) flow).params.toString());
                msg.addReceiver(new AID(resourceName));
                msg.setOntology("Execution");
                Behaviour b = new AchieveREInitiator(this, msg) {

                    @Override
                    protected void handleInform(ACLMessage inform) {
                        System.out.println("Operation " + msg.getContent() + " executed with value " + inform.getContent());
                    }
                };
                return b;
            case DECISION:
                //TODO not implemented just yet!
                return null;
            case PARALLEL:
                ParallelBehaviour pb = new ParallelBehaviour();
                for (FlowDeclaration subFlow : ((Parallel) flow).getSubFlows()) {
                    pb.addSubBehaviour(covertFlowsToBehaviours(subFlow));
                }
                return pb;
            case REPETITION:
                String operator = ((Repetition) flow).operator;
                String operand1 = ((Repetition) flow).operand1;
                String operand2 = null;
                if (((Repetition) flow).operand2 != null) {
                    operand2 = ((Repetition) flow).operand2;
                }
                String numberLiteral2 = null;
                if (((Repetition) flow).numberLiteral2 != null) {
                    numberLiteral2 = ((Repetition) flow).numberLiteral2;
                }

                SequentialBehaviour sb = new SequentialBehaviour();
                for (FlowDeclaration subFlow : ((Repetition) flow).getSubFlows()) {
                    sb.addSubBehaviour(covertFlowsToBehaviours(subFlow));
                }

                String finalOperand = operand2;
                String finalNumberLiteral = numberLiteral2;
                Behaviour terminatorBehaviour = new OneShotBehaviour(this) {

                    private boolean result = false;

                    @Override
                    public void action() {
                        //TODO at the moment only applies to numeric variables, needs to be extended to the other variable types
                        int oper1 = Integer.parseInt(symbolTable.get(operand1).value);
                        int oper2;
                        if (finalOperand != null) oper2 = Integer.parseInt(symbolTable.get(finalOperand).value);
                        else oper2 = Integer.parseInt(finalNumberLiteral);
                        switch (operator) {
                            case "<":
                                result = oper1 < oper2;
                            case ">":
                                result = oper1 > oper2;
                                break;
                            case "==":
                                result = oper1 == oper2;
                                break;
                            case "!=":
                                result = oper1 != oper2;
                                break;
                            case ">=":
                                result = oper1 >= oper2;
                                break;
                            case "<=":
                                result = oper1 <= oper2;
                                break;
                        }
                    }

                    @Override
                    public int onEnd() {
                        if (result) return 0;
                        else return 1;
                    }
                };

                FSMBehaviour repetitionBehaviour = new FSMBehaviour(this);
                repetitionBehaviour.registerFirstState(sb, "Repetition");
                repetitionBehaviour.registerState(terminatorBehaviour, "Termination");
                repetitionBehaviour.registerLastState(new OneShotBehaviour() {
                    @Override
                    public void action() {
                        //TODO nothing to do here!
                    }

                }, "EffectiveTermination");

                repetitionBehaviour.registerDefaultTransition("Repetition", "Termination", new String[]{"Repetition"});
                repetitionBehaviour.registerTransition("Termination", "Repetition", 0, new String[]{"Termination"});
                repetitionBehaviour.registerTransition("Termination", "EffectiveTermination", 1);

                return repetitionBehaviour;

            case SEQUENCE:
                SequentialBehaviour sbSeq = new SequentialBehaviour();
                for (FlowDeclaration subFlow : ((Sequence) flow).getSubFlows()) {
                    sbSeq.addSubBehaviour(covertFlowsToBehaviours(subFlow));
                }
                return sbSeq;
        }
        return null;
    }

    @Override
    protected void setup() {
        String fileName = "C:\\Users\\Luiri60\\Desktop\\AromaRecipe1.txt";
        DynamicRecipeLexer lexer = null;
        try {
            lexer = new DynamicRecipeLexer(CharStreams.fromFileName(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        DynamicRecipeParser parser = new DynamicRecipeParser(tokens);
        ParseTree tree = parser.recipe();
        ParseTreeWalker walker = new ParseTreeWalker();
        SymbolDefinitionPhase def = new SymbolDefinitionPhase();
        walker.walk(def, tree);

        System.out.println("New Recipe uploaded!");
        System.out.println("Parsing Recipe with description:");
        System.out.println(def.getDescription());
        System.out.println("#########################");
        System.out.println("List of Ingredients for the recipe:");
        for (Ingredient ingredient : def.getIngredients().values()) {
            System.out.println(ingredient.toString());
        }
        System.out.println("#########################");
        System.out.println("Preferred Ingredient ratio:");
        for (Map.Entry e : def.getRatio().entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue());
        }
        System.out.println("#########################");
        System.out.println("List of Resources for the recipe");
        for (Resource resource : def.getResources().values()) {
            System.out.println(resource.toString());
        }
        System.out.println("#########################");

        symbolTable = def.getSymbols();

        try {//TODO the sleep is just to allow the other agents to settle into the system not needed in normal operation
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Starting Dynamic Procurement");

        addBehaviour(new SimpleBehaviour() {

            private ProcurementStatus status = ProcurementStatus.IDLE;
            private Map<String, Double> defaultRatio = def.getRatio();

            @Override
            public void action() {
                switch (status) {
                    case IDLE:
                        //TODO nothing to do here at the moment
                        System.out.println("Procurement IDLE");
                        status = ProcurementStatus.START_NEGOTIATION;
                        break;
                    case START_NEGOTIATION:
                        System.out.println("Negotiation Started for ingredients:");
                        for (Map.Entry e : defaultRatio.entrySet()) {
                            System.out.println(e.getKey() + " " + e.getValue());
                            String ingredientName = (String) e.getKey();
                            double quantity = (double) e.getValue();
                            System.out.println(ingredientName + " with quantity " + quantity);
                            ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
                            cfp.setContent(ingredientName + ";" + quantity + ";" + def.getIngredients().get(ingredientName).getRange().getUnit());
                            cfp.setOntology("Negotiation");

                            DFAgentDescription dfAgentDescription = new DFAgentDescription();
                            ServiceDescription serviceDescription = new ServiceDescription();
                            serviceDescription.setType("provider");
                            serviceDescription.setName(ingredientName);
                            dfAgentDescription.addServices(serviceDescription);
                            try {
                                DFAgentDescription[] suppliers = DFService.search(myAgent, dfAgentDescription);
                                //TODO react to the case where there may not be suppliers
                                for (DFAgentDescription supplier : suppliers) {
                                    cfp.addReceiver(supplier.getName());
                                }
                            } catch (FIPAException ex) {
                                throw new RuntimeException(ex);
                            }
                            activeNegotiations.put(ingredientName, new NegotiationResult(ingredientName, quantity));
                            status = ProcurementStatus.WAIT_FOR_RESULTS;
                            addBehaviour(new ProcureBehaviour(myAgent, cfp, ingredientName));

                        }
                        break;
                    case WAIT_FOR_RESULTS:
                        System.out.println("Waiting for procurement results");
                        if (!activeNegotiations.isEmpty()) {
                            boolean procurementComplete = true;
                            for (NegotiationResult neg : activeNegotiations.values()) {
                                if (!neg.isCompleted()) {
                                    procurementComplete = false;
                                    break;
                                }
                            }
                            if (procurementComplete) {
                                System.out.println("Procurement complete");
                                status = ProcurementStatus.EVAL_RESULTS;
                            }
                        }
                        break;
                    case EVAL_RESULTS:
                        System.out.println("Evaluation procurement results");
                        boolean succeeded = true;
                        for (NegotiationResult neg : activeNegotiations.values()) {
                            if (!neg.isResult()) {
                                succeeded = false;
                                break;
                            }
                        }
                        if (succeeded) {
                            status = ProcurementStatus.PROCESS_SUCCESSFUL_NEG;
                        } else {
                            status = ProcurementStatus.PROCESS_FAILED_NEG;
                        }
                        break;
                    case PROCESS_SUCCESSFUL_NEG:
                        System.out.println("Procurement successful");
                        String ingredients = "";
                        boolean first = true;
                        for (NegotiationResult neg : activeNegotiations.values()) {
                            if (!first) {
                                ingredients += "*";
                            } else {
                                first = false;
                            }
                            ingredients += neg.getIngredientName() + ";" + neg.getQuantity();
                        }
                        System.out.println("Adding the following to the execution stack: " + ingredients);
                        rawMaterialAvailable.push(ingredients);
                        block(2000);
                        status = ProcurementStatus.IDLE;
                        break;
                    case PROCESS_FAILED_NEG:
                        System.out.println("Procurement failed due to the following infringing quantities");
                        List<Ingredient> infringingIngredients = new ArrayList<>();
                        for (NegotiationResult neg : activeNegotiations.values()) {
                            if (!neg.isResult()) {
                                System.out.println(neg.getIngredientName() + " with quantity " + defaultRatio.get(neg.getIngredientName()));
                                infringingIngredients.add(def.getIngredients().get(neg.getIngredientName()));
                            }
                        }
                        System.out.println("Recalculating ratios");
                        defaultRatio = recalculateRatio(defaultRatio, infringingIngredients);
                        if (defaultRatio == null) {
                            status = ProcurementStatus.IMPOSSIBLE_NEG;
                        } else {
                            status = ProcurementStatus.START_NEGOTIATION;
                        }
                        break;
                }

            }

            @Override
            public boolean done() {
                if (status == ProcurementStatus.IMPOSSIBLE_NEG) {
                    System.out.println("Procurement is impossible due to insufficient offer, shutting down");
                    return true;
                } else {
                    block(1000);
                    return false;
                }
            }
        });

        FlowDeclaration flow = def.getFlows().pop();
        Behaviour jadeRecipe = covertFlowsToBehaviours(flow);
        SequentialBehaviour sbMain = new SequentialBehaviour();
        sbMain.addSubBehaviour(jadeRecipe);
        sbMain.addSubBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                executing = false;
            }
        });

        addBehaviour(new SimpleBehaviour() {
            @Override
            public void action() {
                if (!executing) {
                    if (!rawMaterialAvailable.isEmpty()) {
                        rawMaterialAvailable.pop();
                        myAgent.addBehaviour(jadeRecipe);
                        executing = true;
                    }
                }

            }

            @Override
            public boolean done() {
                return rawMaterialAvailable.isEmpty();
            }
        });


    }
}

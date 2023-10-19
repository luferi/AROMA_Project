import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.util.*;

public class DynamicRecipeAgent extends Agent {

    private boolean ongoingNegotiation = false;

    Map<String, NegotiationResult> activeNegotiations = new HashMap<>();
    private Stack<String> rawMaterialAvailable = new Stack<>();

    private Map<String, Double> recalculateRatio(Map<String, Double> ratio, List<Ingredient> infringingIngredients){
        Map<String, Double> newRatio = new HashMap<>();
        for(Ingredient ingredient : infringingIngredients){
            Range range = ingredient.getRange();
            switch (range.getType()){
                case NUMERIC:
                    double currentValue = ratio.get(ingredient.getName());
                    if (currentValue - 10 > ((NumericRange)range).min && currentValue - 10 > 0){
                        newRatio.put(ingredient.getName(), currentValue - 10);
                    }else{
                        this is clearly a failed process and the agent must stop
                    }

                    break;
            }
        }

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
        for(Ingredient ingredient : def.getIngredients().values()){
            System.out.println(ingredient.toString());
        }
        System.out.println("#########################");
        System.out.println("Preferred Ingredient ratio:");
        for(Map.Entry e : def.getRatio().entrySet()){
            System.out.println(e.getKey() + " " + e.getValue());
        }
        System.out.println("#########################");
        System.out.println("List of Resources for the recipe");
        for(Resource resource : def.getResources().values()){
            System.out.println(resource.toString());
        }
        System.out.println("#########################");

        addBehaviour(new SimpleBehaviour() {
            @Override
            public void action() {
                if(!ongoingNegotiation){
                    for(Map.Entry e : def.getRatio().entrySet()){
                        System.out.println(e.getKey() + " " + e.getValue());
                        String ingredientName = (String) e.getKey();
                        double quantity = (double) e.getValue();
                        ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
                        cfp.setContent(ingredientName + ";" + quantity);
                        addBehaviour(new ProcureBehaviour(myAgent, cfp, ingredientName));
                        ongoingNegotiation = true;
                    }
                }else{
                    boolean procurementComplete = true;
                    for(NegotiationResult neg : activeNegotiations.values()){
                        if (!neg.isCompleted()) {
                            procurementComplete = false;
                            break;
                        }

                    }


                    if (procurementComplete){
                        boolean succeeded = true;
                        for(NegotiationResult neg : activeNegotiations.values()){
                            if (!neg.isResult()) {
                                succeeded = false;
                                break;
                            }
                        }

                        if(succeeded){
                            String ingredients = "";
                            boolean first = true;
                            for(NegotiationResult neg : activeNegotiations.values()){
                                if(!first){
                                    ingredients += ";";
                                }else{
                                    first = false;
                                }
                                ingredients += neg.getIngredient().getName() + ";" +  neg.getQuantity();
                            }
                            rawMaterialAvailable.push(ingredients);
                            ongoingNegotiation = false;

                        }else{ //not succeeded this is the case for renegotiation

                            List<Ingredient> infringingIngredients = new ArrayList<>();
                            for(NegotiationResult neg : activeNegotiations.values()){
                                if (!neg.isResult()) {
                                    infringingIngredients.add(neg.getIngredient());
                                }
                            }

                            for(Map.Entry e : def.getRatio().entrySet()){
                                System.out.println(e.getKey() + " " + e.getValue());
                                String ingredientName = (String) e.getKey();
                                double quantity = (double) e.getValue();
                                ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
                                cfp.setContent(ingredientName + ";" + quantity);
                                addBehaviour(new ProcureBehaviour(myAgent, cfp, ingredientName));
                                ongoingNegotiation = true;
                            }
                        }

                    }





                }

            }

            @Override
            public boolean done() {
                return false;
            }
        });

    }
}

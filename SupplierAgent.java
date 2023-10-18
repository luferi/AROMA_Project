import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.*;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.ContractNetResponder;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class SupplierAgent extends Agent {

    Map<String, Ingredient> suppliedIngredients = new HashMap<>();

    @Override
    protected void setup() {
        Ingredient water = new Ingredient("water", 10000.0, "ml", 1);
        Ingredient sweetener = new Ingredient("sweetener", 10000.0, "g", 2);
        Ingredient sugar = new Ingredient("sugar", 10000.0, "g", 2);

        Object[] args = getArguments();
        String supplierProfile = (String) args[0];
        switch (supplierProfile) {
            case "0":
                suppliedIngredients.put(water.getName(), water);
                suppliedIngredients.put(sweetener.getName(), sweetener);
                break;
            case "1":
                suppliedIngredients.put(sugar.getName(), sugar);
                break;
            case "2":
                suppliedIngredients.put(water.getName(), water);
                suppliedIngredients.put(sweetener.getName(), sweetener);
                suppliedIngredients.put(sugar.getName(), sugar);
                break;
            default:
                suppliedIngredients.put(water.getName(), water);
        }

        DFAgentDescription dfAgentDescription = new DFAgentDescription();

        for (Ingredient value : suppliedIngredients.values()) {
            ServiceDescription serviceDescription = new ServiceDescription();
            serviceDescription.setName(value.getName());
            serviceDescription.setType("provider");
            dfAgentDescription.addServices(serviceDescription);
        }
        try {
            DFService.register(this, dfAgentDescription);
        } catch (FIPAException e) {
            throw new RuntimeException(e);
        }


        addBehaviour(new ContractNetResponder(this, MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.CFP),
                MessageTemplate.MatchOntology("Negotiation"))) {

            @Override
            protected ACLMessage handleCfp(ACLMessage cfp) throws RefuseException, FailureException, NotUnderstoodException {
                ACLMessage reply = cfp.createReply();
                reply.setOntology("Negotiation");

                String content = cfp.getContent();
                StringTokenizer tokenizer = new StringTokenizer(content, ";");
                String ingredientName = tokenizer.nextToken();
                String ingredientQuantity = tokenizer.nextToken();
                String ingredientQuantityUnit = tokenizer.nextToken();

                Ingredient selected = suppliedIngredients.get(ingredientName);
                if (selected != null
                        && selected.getQuantityUnit().equalsIgnoreCase(ingredientQuantityUnit)
                        && selected.getSuppliedQuantity() >= Double.parseDouble(ingredientQuantity)) {
                    double priceOnOffer = selected.getUnitPrice() / selected.getSuppliedQuantity();
                    reply.setContent(String.valueOf(priceOnOffer));
                    reply.setPerformative(ACLMessage.PROPOSE);
                    return reply;
                }
                reply.setPerformative(ACLMessage.REFUSE);
                reply.setContent("Product not currently available");
                return reply;
            }

            @Override
            protected ACLMessage handleAcceptProposal(ACLMessage cfp, ACLMessage propose, ACLMessage accept) throws FailureException {
                ACLMessage reply = accept.createReply();
                reply.setOntology("Negotiation");
                reply.setPerformative(ACLMessage.INFORM);

                String content = cfp.getContent();
                StringTokenizer tokenizer = new StringTokenizer(content, ";");
                String ingredientName = tokenizer.nextToken();
                String ingredientQuantity = tokenizer.nextToken();

                Ingredient selected = suppliedIngredients.get(ingredientName);
                selected.setSuppliedQuantity(selected.getSuppliedQuantity() - Double.parseDouble(ingredientQuantity));

                reply.setContent(ingredientQuantity);
                return reply;
            }
        });
    }
}

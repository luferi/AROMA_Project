import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.proto.ContractNetInitiator;

import java.util.Vector;

public class ProcureBehaviour extends ContractNetInitiator {

    private String ingredientName;

    public ProcureBehaviour(Agent a, ACLMessage cfp, String ingredientName) {
        super(a, cfp);
        this.ingredientName = ingredientName;
    }

    @Override
    protected void handleAllResponses(Vector responses, Vector acceptances) {
        ACLMessage bestProposal = null;
        double bestPrice = 200000.0;

        for (Object response : responses.stream().toList()) {
            if (((ACLMessage) response).getPerformative() == ACLMessage.PROPOSE) {
                double price = Double.parseDouble(((ACLMessage) response).getContent());
                if (price < bestPrice) {
                    bestProposal = ((ACLMessage) response);
                    bestPrice = price;
                }
            }
        }

        if (bestProposal != null) {
            ACLMessage reply = bestProposal.createReply();
            reply.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
            reply.setContent("I accept your offer");
            acceptances.add(reply);
        } else {
            ((DynamicRecipeAgent)myAgent).activeNegotiations.get(ingredientName).setResult(false);
            ((DynamicRecipeAgent)myAgent).activeNegotiations.get(ingredientName).setCompleted(true);
        }

    }

    @Override
    protected void handleInform(ACLMessage inform) {
        ((DynamicRecipeAgent)myAgent).activeNegotiations.get(ingredientName).setResult(true);
        ((DynamicRecipeAgent)myAgent).activeNegotiations.get(ingredientName).setCompleted(true);
    }
}

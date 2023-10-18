import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.proto.ContractNetInitiator;

import java.util.Vector;

public class ProcureBehaviour extends ContractNetInitiator {
    public ProcureBehaviour(Agent a, ACLMessage cfp) {
        super(a, cfp);
    }

    @Override
    protected void handleAllResponses(Vector responses, Vector acceptances) {
        ACLMessage bestProposal = null;
        double bestPrice = 200000.0;

        for(Object response : responses.stream().toList()){
            if(((ACLMessage)response).getPerformative() == ACLMessage.PROPOSE){
                double price = Double.parseDouble(((ACLMessage)response).getContent());
                if (price < bestPrice){
                    bestProposal = ((ACLMessage)response);
                    bestPrice = price;
                }
            }
        }

        aqui testar se houve alguma oferta e aceitar ou errar em conformidade!
                uma idea é se nao houve ofertas immediatamente recalcular os racios e fazer novo CFP
    }

    @Override
    protected void handleAllResultNotifications(Vector resultNotifications) {
        super.handleAllResultNotifications(resultNotifications);
    }
}

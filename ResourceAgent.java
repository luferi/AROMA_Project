import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.AchieveREResponder;

import java.util.HashMap;
import java.util.Map;

public class ResourceAgent extends Agent {

    private Map<String, String> funcs = new HashMap<>();

    @Override
    protected void setup() {
        Object[] args = getArguments();
        for (Object funcName : args) {
            String name = funcName.toString();
            funcs.put(name, name);
        }

        DFAgentDescription dfAgentDescription = new DFAgentDescription();

        for (String func : funcs.values()) {
            ServiceDescription serviceDescription = new ServiceDescription();
            serviceDescription.setName(func);
            serviceDescription.setType("resource");
            dfAgentDescription.addServices(serviceDescription);
        }
        try {
            DFService.register(this, dfAgentDescription);
        } catch (FIPAException e) {
            throw new RuntimeException(e);
        }

        addBehaviour(new AchieveREResponder(this, MessageTemplate.and(MessageTemplate.MatchOntology("Execution"), MessageTemplate.MatchPerformative(ACLMessage.REQUEST))) {
            @Override
            protected ACLMessage handleRequest(ACLMessage request) throws NotUnderstoodException, RefuseException {
                ACLMessage reply = request.createReply();
                System.out.println("Executing the following request " + request.getContent());
                reply.setPerformative(ACLMessage.INFORM);
                reply.setContent("60;t");
                return reply;
            }
        });
    }
}

import java.util.ArrayList;
import java.util.List;

public class Repetition implements FlowDeclaration{

     String operand1;
     String operand2;

     String numberLiteral2;

     String operator;

    private List<FlowDeclaration> subFlows;

    public Repetition() {
        subFlows = new ArrayList<>();
    }


    @Override
    public FlowType getType() {
        return FlowType.REPETITION;
    }

    @Override
    public void addFlow(FlowDeclaration flow) {
        subFlows.add(flow);
    }
}

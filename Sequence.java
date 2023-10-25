import java.util.ArrayList;
import java.util.List;

public class Sequence implements FlowDeclaration {

    private List<FlowDeclaration> subFlows;

    public Sequence() {
        subFlows = new ArrayList<>();
    }


    @Override
    public FlowType getType() {
        return FlowType.SEQUENCE;
    }

    @Override
    public void addFlow(FlowDeclaration flow) {
        subFlows.add(flow);
    }

    public List<FlowDeclaration> getSubFlows() {
        return subFlows;
    }
}

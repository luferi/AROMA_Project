import java.util.ArrayList;
import java.util.List;

public class Parallel implements FlowDeclaration {

    private List<FlowDeclaration> subFlows;

    public Parallel() {
        subFlows = new ArrayList<>();
    }


    @Override
    public FlowType getType() {
        return FlowType.PARALLEL;
    }

    @Override
    public void addFlow(FlowDeclaration flow) {
        subFlows.add(flow);
    }

}

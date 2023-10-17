import java.util.List;

public class Atomic implements FlowDeclaration{

    String resource;
    String function;

    List<String> params;

    @Override
    public FlowType getType() {
        return FlowType.ATOMIC;
    }

    @Override
    public void addFlow(FlowDeclaration flow) {
        System.out.println("Does not apply to Atomic");
    }


}

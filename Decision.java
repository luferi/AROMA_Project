public class Decision implements FlowDeclaration{

    String operand1;
    String operand2;

    String numberLiteral2;

    String operator;


    FlowDeclaration ifTrue;
    FlowDeclaration ifFalse;


    public Decision() {
    }

    @Override
    public FlowType getType() {
        return FlowType.DECISION;
    }

    @Override
    public void addFlow(FlowDeclaration flow) {
        System.out.println("Does not apply to Decision");
    }

    public void setIfTrue(FlowDeclaration ifTrue) {
        this.ifTrue = ifTrue;
    }

    public void setIfFalse(FlowDeclaration ifFalse) {
        this.ifFalse = ifFalse;
    }
}

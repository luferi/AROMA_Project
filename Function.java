import java.util.List;

public class Function {

    String name;
    List<Parameter> inputParameters;
    List<Parameter> outputParameters;

    @Override
    public String toString() {
        return "Function{" +
                "name='" + name + '\'' +
                ", inputParameters=" + inputParameters +
                ", outputParameters=" + outputParameters +
                '}';
    }
}

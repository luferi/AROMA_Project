import java.util.List;

public class Resource {

    String name;
    List<Function> functions;

    @Override
    public String toString() {
        return "Resource{" +
                "name='" + name + '\'' +
                ", functions=" + functions +
                '}';
    }
}

public class Parameter {
    String name;
    Range range;
    AromaType type;

    @Override
    public String toString() {
        return "Parameter{" +
                "name='" + name + '\'' +
                ", range=" + range +
                ", type=" + type +
                '}';
    }
}

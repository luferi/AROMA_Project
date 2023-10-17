public class Symbol {

    String name;      // All symbols at least have a name
    AromaType type;

    String value;

    public Symbol(String name, AromaType type) { this.name = name; this.type = type; }
    public String getName() { return name; }


}
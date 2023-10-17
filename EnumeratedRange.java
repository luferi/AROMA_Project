import java.util.List;

public class EnumeratedRange implements Range{

    List<String> values;

    @Override
    public boolean isInRange(Object obj) {
        return values.contains(obj);
    }
}

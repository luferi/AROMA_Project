import java.util.List;

public class EnumerateRange implements Range{
    List<String> admissableValues;

    public EnumerateRange(List<String> admissibleValues) {
        this.admissableValues = admissibleValues;
    }

    @Override
    public boolean isInRange(Object obj) {
        return admissableValues.contains(obj.toString());
    }
}

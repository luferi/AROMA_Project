import java.util.List;

public class EnumeratedRange implements Range {
    List<String> admissableValues;

    @Override
    public boolean isInRange(Object obj) {
        return admissableValues.contains(obj.toString());
    }

    @Override
    public RangeType getType() {
        return RangeType.ENUMERATED;
    }

    @Override
    public String toString() {
        return "EnumeratedRange{" +
                "admissableValues=" + admissableValues +
                '}';
    }


}

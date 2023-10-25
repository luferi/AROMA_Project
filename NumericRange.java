public class NumericRange implements Range{

    int min;
    int max;

    String unit;

    public NumericRange(int min, int max, String unit) {
        this.min = min;
        this.max = max;
        String result = unit.substring(1, unit.length() - 1);
        this.unit = result;
    }

    @Override
    public boolean isInRange(Object obj) {
        int value = Integer.parseInt(obj.toString());
        return min < value && value < max;
    }

    @Override
    public RangeType getType() {
        return RangeType.NUMERIC;
    }

    @Override
    public String getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        return "NumericRange{" +
                "min=" + min +
                ", max=" + max +
                '}';
    }
}

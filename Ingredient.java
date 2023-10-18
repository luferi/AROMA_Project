public class Ingredient {
    private String name;
    private Range range;

    private double suppliedQuantity;

    private String quantityUnit;

    private double unitPrice;

    public Ingredient() {
    }

    public Ingredient(String name, Range range) {
        this.name = name;
        this.range = range;
    }


    public Ingredient(String name, double suppliedQuantity, String quantityUnit, double unitPrice) {
        this.name = name;
        this.suppliedQuantity = suppliedQuantity;
        this.quantityUnit = quantityUnit;
        this.unitPrice = unitPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Range getRange() {
        return range;
    }

    public void setRange(Range range) {
        this.range = range;
    }

    public double getSuppliedQuantity() {
        return suppliedQuantity;
    }

    public void setSuppliedQuantity(double suppliedQuantity) {
        this.suppliedQuantity = suppliedQuantity;
    }

    public String getQuantityUnit() {
        return quantityUnit;
    }

    public void setQuantityUnit(String quantityUnit) {
        this.quantityUnit = quantityUnit;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + name + '\'' +
                ", range=" + range.toString() +
                ", suppliedQuantity=" + suppliedQuantity +
                ", quantityUnit='" + quantityUnit + '\'' +
                ", unitPrice=" + unitPrice +
                '}';
    }
}

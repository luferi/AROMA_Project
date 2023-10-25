public class NegotiationResult {

    private String ingredientName;
    private boolean result;
    private double quantity;
    private boolean deviatesFromIdeal;
    private double deviation;

    private boolean completed;

    public NegotiationResult(String ingredientName, double quantity) {
        this.ingredientName = ingredientName;
        this.result = false;
        this.quantity = quantity;
        this.deviatesFromIdeal = false;
        this.deviation = 0.0;
        this.completed = false;
    }


    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public boolean isDeviatesFromIdeal() {
        return deviatesFromIdeal;
    }

    public void setDeviatesFromIdeal(boolean deviatesFromIdeal) {
        this.deviatesFromIdeal = deviatesFromIdeal;
    }

    public double getDeviation() {
        return deviation;
    }

    public void setDeviation(double deviation) {
        this.deviation = deviation;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}

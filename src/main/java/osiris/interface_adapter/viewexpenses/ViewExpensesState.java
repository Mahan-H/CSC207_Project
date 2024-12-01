package osiris.interface_adapter.viewexpenses;

/**
 * The state for the ViewExpenses Model.
 */

public class ViewExpensesState {
    private double essential;
    private double nonEssential;

    public double getEssential() {
        return essential;
    }

    public double getNonEssential() {
        return nonEssential;
    }

    public void setEssential(double essential) {
        this.essential = essential;
    }

    public void setNonEssential(double nonEssential) {
        this.nonEssential = nonEssential;
    }
}

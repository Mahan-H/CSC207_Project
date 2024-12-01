package osiris.use_case.viewexpenses;

/**
 * ViewExpenses Output Data.
 */
public class ViewExpensesOutputData {
    private final double essentialTotal;
    private final double nonEssentialTotal;

    public ViewExpensesOutputData(double essentialTotal, double nonEssentialTotal) {
        this.essentialTotal = essentialTotal;
        this.nonEssentialTotal = nonEssentialTotal;
    }

    public double getEssentialTotal() {
        return essentialTotal;
    }

    public double getNonEssentialTotal() {
        return nonEssentialTotal;
    }
}

package osiris.use_case.budget;

import java.util.ArrayList;

/**
 * ViewExpenses Output Data.
 */
public class BudgetOutputData {
    private final ArrayList<Double> monthlyTotals;

    public BudgetOutputData(ArrayList<Double> monthlyTotals) {
        this.monthlyTotals = monthlyTotals;
    }

    public ArrayList<Double> getMonthlyTotals() {
        return monthlyTotals;
    }
}

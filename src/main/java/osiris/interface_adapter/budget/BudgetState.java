package osiris.interface_adapter.budget;

import java.util.ArrayList;

/**
 * The state for the budget Model.
 */

public class BudgetState {
    private ArrayList<Double> monthlyTotals;

    public ArrayList<Double> getMonthlyTotals() {
        return monthlyTotals;
    }

    public void setMonthlyTotals(ArrayList<Double> monthlyTotals) {
        this.monthlyTotals = monthlyTotals;
    }
}

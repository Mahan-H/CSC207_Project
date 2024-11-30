package osiris.interface_adapter.viewexpenses;

import lombok.Getter;

/**
 * The state for the ViewExpenses Model.
 */
@Getter
public class ViewExpensesState {
    private final double essential;
    private final double nonEssential;

    public ViewExpensesState(double essential, double nonEssential) {
        this.essential = essential;
        this.nonEssential = nonEssential;
    }
}

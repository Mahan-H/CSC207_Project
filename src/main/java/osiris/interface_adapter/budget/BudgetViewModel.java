package osiris.interface_adapter.budget;

import osiris.interface_adapter.ViewModel;

/**
 * The ViewModel for the budget.
 */
public class BudgetViewModel extends ViewModel<BudgetState> {
    public static final String TITLE_LABEL = "Monthly Spending";
    public static final String BUTTON_LABEL = "Budget";
    public static final String BACK_LABEL = "Back";

    public BudgetViewModel() {

        super("budget");
        setState(new BudgetState());
    }

}


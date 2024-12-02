package osiris.interface_adapter.dashboard;

/**
 * The ViewModel for the Welcome View.
 */
public class DashboardViewModel {

    public static final String ADD_BANK_ACCOUNT_BUTTON_LABEL = "Add Bank";
    public static final String VIEW_BUDGET_BUTTON_LABEL = "View Budget";
    public static final String VIEW_SHAHCASE_BUTTON_LABEL = "Shah Case";
    public static final String VIEW_TRANSACTION_HISTORY_BUTTON_LABEL = "View Expenses";

    public DashboardViewModel() {
    }

    public String getViewName() {
        return "dashboard";
    }
}

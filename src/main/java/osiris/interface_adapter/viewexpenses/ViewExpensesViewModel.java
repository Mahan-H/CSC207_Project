package osiris.interface_adapter.viewexpenses;

import osiris.interface_adapter.ViewModel;

/**
 * The ViewModel for the ViewExpenses.
 */
public class ViewExpensesViewModel extends ViewModel<ViewExpensesState> {
    public static final String TITLE_LABEL = "Essentials vs Non-Essentials";
    public static final String BUTTON_LABEL = "Chart";
    public static final String BACK_LABEL = "Back";

    public ViewExpensesViewModel() {

        super("view expenses");
        setState(new ViewExpensesState());
    }

}

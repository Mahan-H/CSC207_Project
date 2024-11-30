package osiris.interface_adapter.viewexpenses;

import osiris.interface_adapter.ViewModel;
import osiris.interface_adapter.signup.SignupState;

public class ViewExpensesViewModel extends ViewModel<ViewExpensesViewModel> {
        public static final String TITLE_LABEL = "Essentials vs Non-Essentials";
        public static final String Button_LABEL = "Chart";
        public static final String Back_label = "Back";


    public ViewExpensesViewModel() {
        super("view expenses");
    }
}

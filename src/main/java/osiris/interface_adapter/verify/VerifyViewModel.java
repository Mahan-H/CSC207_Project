package osiris.interface_adapter.verify;

import osiris.interface_adapter.ViewModel;

/**
 * The View Model for the Logged In View.
 */

public class VerifyViewModel extends ViewModel<VerifyState> {
    public static final String TITLE_LABEL = "Verify Your Account";
    public static final String VERIFY_LABEL = "Enter the Code: ";
    public static final String VERIFY_BUTTON_LABEL = "Verify";
    public static final String RETURN_BUTTON_LABEL = "Go Back";

    public VerifyViewModel() {
        super("verify");
        setState(new VerifyState());
    }

}

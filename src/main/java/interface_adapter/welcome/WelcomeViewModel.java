package interface_adapter.welcome;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Welcome View.
 */
public class WelcomeViewModel extends ViewModel<WelcomeState> {

    public static final String TITLE_LABEL = "Welcome to Osiris!";
    public static final String SLOGAN_LABEL = "Your path to financial freedom";

    public static final String CREATE_ACCOUNT_BUTTON_LABEL = "Create Account";
    public static final String LOGIN_BUTTON_LABEL = "Login";

    public WelcomeViewModel() {
        super("welcome");
        setState(new WelcomeState());
    }
}

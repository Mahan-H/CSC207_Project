package osiris.interface_adapter.welcome;

/**
 * The ViewModel for the Welcome View.
 */
public class WelcomeViewModel {

    public static final String TITLE_LABEL = "Welcome to Osiris!";
    public static final String SLOGAN_LABEL = "Your path to financial freedom";

    public static final String CREATE_ACCOUNT_BUTTON_LABEL = "Create Account";
    public static final String LOGIN_BUTTON_LABEL = "Login";

    public WelcomeViewModel() {
    }

    public String getViewName() {
        return "welcome";
    }
}

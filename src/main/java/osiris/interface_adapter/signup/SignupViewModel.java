package osiris.interface_adapter.signup;

import osiris.interface_adapter.ViewModel;

/**
 * The ViewModel for the View.
 */
public class SignupViewModel extends ViewModel<SignupState> {

    public static final String TITLE_LABEL = "Create Your Account";
    public static final String EMAIL_LABEL = "Type Your Email";
    public static final String PASSWORD_LABEL = "Choose password";
    public static final String REPEAT_PASSWORD_LABEL = "Enter password again";

    public static final String SIGNUP_BUTTON_LABEL = "Create Account";
    public static final String RETURN_BUTTON_LABEL = "Go back";

    public SignupViewModel() {
        super("sign up");
        setState(new SignupState());
    }

}

package osiris.interface_adapter.signup;

import osiris.use_case.signup.SignupInputBoundary;
import osiris.use_case.signup.SignupInputData;

/**
 * Controller for the Signup Use Case.
 */
public class SignupController {

    private final SignupInputBoundary userSignupUseCaseInteractor;

    public SignupController(SignupInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    /**
     * Executes the Signup Use Case.
     * @param email the username to sign up
     * @param password1 the password
     * @param password2 the password repeated
     * @param access_code the access code
     */
    public void execute(String email, String password1, String password2, String access_code) {
        final SignupInputData signupInputData = new SignupInputData(
                email, password1, password2, access_code);

        userSignupUseCaseInteractor.execute(signupInputData);
    }

    /**
     * Executes the "switch to LoginView" Use Case.
     */
    public void switchToVerifyView() {
        userSignupUseCaseInteractor.switchToVerifyView();
    }

    /**
     * Executes the "switch to WelcomeView" Use Case.
     */
    public void switchToWelcomeView() {
        userSignupUseCaseInteractor.switchToWelcomeView();
    }
}

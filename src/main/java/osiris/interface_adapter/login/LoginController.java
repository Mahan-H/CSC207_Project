package osiris.interface_adapter.login;

import osiris.use_case.login.LoginInputBoundary;
import osiris.use_case.login.LoginInputData;

/**
 * The controller for the Login Use Case.
 */
public class LoginController {

    private final LoginInputBoundary loginUseCaseInteractor;

    public LoginController(LoginInputBoundary loginUseCaseInteractor) {
        this.loginUseCaseInteractor = loginUseCaseInteractor;
    }

    /**
     * Executes the Login Use Case.
     * @param username the username of the user logging in
     * @param password the password of the user logging in
     * @param access_code the access code of the user logging in
     */
    public void execute(String username, String password, String access_code) {
        final LoginInputData loginInputData = new LoginInputData(
                username, password, access_code);

        loginUseCaseInteractor.execute(loginInputData);
    }

    /**
     * Executes the "switch to WelcomeView" Use Case.
     */
    public void switchToWelcomeView() {
        loginUseCaseInteractor.switchToWelcomeView();
    }
}

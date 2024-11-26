package osiris.interface_adapter.welcome;

import osiris.use_case.welcome.WelcomeInputBoundary;

/**
 * Controller for the Welcome Use Case.
 */
public class WelcomeController {

    private final WelcomeInputBoundary userWelcomeUseCaseInteractor;

    public WelcomeController(WelcomeInputBoundary userWelcomeUseCaseInteractor) {
        this.userWelcomeUseCaseInteractor = userWelcomeUseCaseInteractor;
    }

    /**
     * Executes the "switch to LoginView" Use Case.
     */
    public void switchToLoginView() {
        userWelcomeUseCaseInteractor.switchToLoginView();
    }

    /**
     * Executes the "switch to SignUpView" Use Case.
     */
    public void switchToSignUpView() {
        userWelcomeUseCaseInteractor.switchToSignUpView();
    }
}

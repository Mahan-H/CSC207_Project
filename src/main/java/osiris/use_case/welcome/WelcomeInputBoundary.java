package osiris.use_case.welcome;

/**
 * Input boundary for the Welcome Use Case.
 */
public interface WelcomeInputBoundary {

    /**
     * Executes the switch to login view use case.
     */
    void switchToLoginView();

    /**
     * Executes the switch to sign up view use case.
     */
    void switchToSignUpView();
}

package osiris.use_case.welcome;

/**
 * Output boundary for the Welcome Use Case.
 */
public interface WelcomeOutputBoundary {

    /**
     * Switches to the Login View.
     */
    void switchToLoginView();

    /**
     * Switches to the Sign up View.
     */
    void switchToSignUpView();
}

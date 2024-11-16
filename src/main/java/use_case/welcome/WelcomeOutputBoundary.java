package use_case.welcome;

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

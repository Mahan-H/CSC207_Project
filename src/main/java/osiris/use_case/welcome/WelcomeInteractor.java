package osiris.use_case.welcome;

/**
 * Interactor for the Welcome Use Case.
 */
public class WelcomeInteractor implements WelcomeInputBoundary {

    private final WelcomeOutputBoundary userPresenter;

    public WelcomeInteractor(WelcomeOutputBoundary welcomeOutputBoundary) {
        this.userPresenter = welcomeOutputBoundary;
    }

    @Override
    public void switchToLoginView() {
        userPresenter.switchToLoginView();
    }

    @Override
    public void switchToSignUpView() {
        userPresenter.switchToSignUpView();
    }

}

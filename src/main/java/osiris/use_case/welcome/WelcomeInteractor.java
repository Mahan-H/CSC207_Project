package osiris.use_case.welcome;

import osiris.entity.UserFactory;

/**
 * Interactor for the Welcome Use Case.
 */
public class WelcomeInteractor implements WelcomeInputBoundary {

    private final UserFactory userFactory;
    private final WelcomeOutputBoundary userPresenter;

    public WelcomeInteractor(WelcomeOutputBoundary welcomeOutputBoundary, UserFactory userFactory) {
        this.userPresenter = welcomeOutputBoundary;
        this.userFactory = userFactory;
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

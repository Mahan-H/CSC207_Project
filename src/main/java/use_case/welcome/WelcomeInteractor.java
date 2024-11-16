package use_case.welcome;

import entity.UserFactory;

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

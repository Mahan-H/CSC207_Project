package use_case.verify;

/**
 * The Verify Email Interactor.
 */

public class VerifyInteractor implements VerifyInputBoundary {
    private final VerifyOutputBoundary userPresenter;

    public VerifyInteractor(VerifyOutputBoundary verifyOutputBoundary) {
        this.userPresenter = verifyOutputBoundary;
    }

    @Override
    public void execute(VerifyInputData verifyInputData) {
        final VerifyOutputData verifyOutputData = new VerifyOutputData(verifyInputData.getVerifyCode(), false);
        userPresenter.prepareSuccessView(verifyOutputData);
    }

    @Override
    public void switchToSignUpView() {
        userPresenter.switchToSignUpView();
    }
}

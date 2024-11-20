package interface_adapter.verify;

import use_case.verify.VerifyInputBoundary;
import use_case.verify.VerifyInputData;

/**
 * Controller for the verify Use Case.
 */

public class VerifyController {
    private final VerifyInputBoundary verifyUseCaseInteractor;

    public VerifyController(VerifyInputBoundary verifyUseCaseInteractor) {
        this.verifyUseCaseInteractor = verifyUseCaseInteractor;
    }

    /**
     * Executes the Verify Email Use Case.
     * @param username the user whose password to change
     */

    public void execute(String username) {
        final VerifyInputData verifyInputData = new VerifyInputData(username);
        verifyUseCaseInteractor.execute(verifyInputData);
    }

    /**
     * Executes the "switch to LoginView" Use Case.
     */

    public void switchToSignUpView() {
        verifyUseCaseInteractor.switchToSignUpView();
    }

    /**
     * Resends the verification email to the user.
     * @param username the user requesting a new verification email
     */
    public void resendVerificationEmail(String username) {
        final VerifyInputData inputData = new VerifyInputData(username);
        verifyUseCaseInteractor.resendVerificationEmail(inputData);
    }

}

package osiris.use_case.verify;

/**
 * The Verify Email Use Case.
 */
public interface VerifyInputBoundary {
    /**
     * Execute the Verify Email Use Case.
     * @param verifyInputData the input data for this use case
     */

    void execute(VerifyInputData verifyInputData);

    /**
     * Prepares the switch to signup view for the Verify Email Use Case.
     */
    void switchToSignUpView();

    /**
     * Prepares the resending for Verify Email Use Case.
     * @param verifyInputData the input data for this use case
     */
    void resendVerificationEmail(VerifyInputData verifyInputData);

}

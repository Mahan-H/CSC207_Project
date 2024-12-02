package osiris.use_case.verify;

/**
 * The output boundary for the Verify User Use Case.
 */

public interface VerifyOutputBoundary {
    /**
     * Prepares the success view for the Verify User Use Case.
     * @param outputData the output data
     */

    void prepareSuccessView(VerifyOutputData outputData);
    /**
     * Prepares the failure view for the Verify User Use Case.
     * @param errorMessage the explanation of the failure
     */

    void prepareFailView(String errorMessage);

    void switchToSignUpView();
}

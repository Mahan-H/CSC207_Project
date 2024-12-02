package osiris.use_case.verify;

/**
 * The Verify User Use Case.
 */
public interface VerifyInputBoundary {
    /**
     * Execute the Verify User Use Case.
     * @param verifyInputData the input data for this use case
     */

    void execute(VerifyInputData verifyInputData);

    void switchToSignUpView();

    /**
     * Generate a CAPTCHA for the user.
     * @param sessionId The unique session ID for the user.
     * @return The generated CAPTCHA string.
     */
    String generateCaptcha(String sessionId);

    /**
     * Validate the user's entered CAPTCHA.
     * @param captchaCode The CAPTCHA code entered by the user.
     * @param sessionId The unique session ID for the user.
     * @return true if the CAPTCHA is valid, false otherwise.
     */
    boolean validateCaptcha(String captchaCode, String sessionId);

    void switchToDashboardView();

}


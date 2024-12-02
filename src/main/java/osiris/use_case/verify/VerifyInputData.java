package osiris.use_case.verify;

/**
 * The input data for the Verify Email Use Case.
 */

public class VerifyInputData {
    private final String user;
    private final String captchaResponse;

    /**
     * Constructs a new VerifyInputData instance with the specified email and verification code.
     *
     * @param user the user's username
     * @param captchaResponse the response to the CAPTCHA
     */
    public VerifyInputData(String user, String captchaResponse) {
        this.user = user;
        this.captchaResponse = captchaResponse;
    }

    /**
     * Returns the user's username.
     *
     * @return the username
     */
    public String getUser() {
        return user;
    }

    public String getCaptchaResponse() {
        return captchaResponse;
    }
}

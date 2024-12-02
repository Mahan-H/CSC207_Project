package osiris.interface_adapter.verify;

/**
 * The State information representing the logged-in user.
 */
public class VerifyState {
    private String username = "";
    private String captchaCode = "";
    private String captchaError;
    private boolean captchaValidation;


    // Copy constructor
    public VerifyState(VerifyState copy) {
        this.username = copy.username;
        this.captchaCode = copy.captchaCode;
        this.captchaError = copy.captchaError;
    }

    // Default constructor
    public VerifyState() {
    }

    // Getter for username
    public String getUsername() {
        return username;
    }

    // Setter for username
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter for CAPTCHA code
    public String getCaptchaCode() {
        return captchaCode;
    }

    // Setter for CAPTCHA code
    public void setCaptchaCode(String captchaCode) {
        this.captchaCode = captchaCode;
    }

    // Getter for CAPTCHA error
    public String getCaptchaError() {
        return captchaError;
    }

    // Setter for CAPTCHA error
    public void setCaptchaError(String captchaError) {
        this.captchaError = captchaError;
    }

    public boolean isCaptchaValidation() {
        return captchaValidation;
    }

    public void setCaptchaValidation(boolean isValid) {
        this.captchaValidation = isValid;
    }
}

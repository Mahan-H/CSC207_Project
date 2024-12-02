package osiris.use_case.verify;

/**
 * Output Data for the Verify User Use Case.
 */

public class VerifyOutputData {
    private final String username;
    private final boolean useCaseFailed;
    private final String captcha;

    public VerifyOutputData(String username, boolean useCaseFailed, String captcha) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
        this.captcha = captcha;
    }

    public String getCaptcha() {
        return captcha;
    }
}

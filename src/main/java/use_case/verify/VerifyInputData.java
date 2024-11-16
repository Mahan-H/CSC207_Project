package use_case.verify;

/**
 * The input data for the Verify Email Use Case.
 */

public class VerifyInputData {
    private final String verifyCode;

    public VerifyInputData(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getVerifyCode() {
        return verifyCode;
    }
}

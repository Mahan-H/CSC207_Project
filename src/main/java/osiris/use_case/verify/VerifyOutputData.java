package osiris.use_case.verify;

/**
 * Output Data for the Verify Email Use Case.
 */

public class VerifyOutputData {
    private final String username;
    private final boolean useCaseFailed;

    public VerifyOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }
}

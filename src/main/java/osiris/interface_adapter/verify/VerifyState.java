package osiris.interface_adapter.verify;

/**
 * The State information representing the logged-in user.
 */
public class VerifyState {
    private String username = "";
    private String verifyCodeError;

    public VerifyState(VerifyState copy) {
        username = copy.username;
        verifyCodeError = copy.verifyCodeError;
    }
    // Because of the previous copy constructor, the default constructor must be explicit.

    public VerifyState() {
    }

    public String getUsername() {
        return username;
    }

    public void setVerifyCodeError(String verifyCodeError) {
        this.verifyCodeError = verifyCodeError;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

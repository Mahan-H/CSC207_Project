package osiris.use_case.login;

/**
 * Output Data for the Login Use Case.
 */
public class LoginOutputData {

    private final String user;
    private final boolean useCaseFailed;

    public LoginOutputData(String user, boolean useCaseFailed) {
        this.user = user;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUser() {
        return user;
    }

}

package osiris.use_case.login;

/**
 * Output Data for the Login Use Case.
 */
public class LoginOutputData {

    private final String email;
    private final boolean useCaseFailed;

    public LoginOutputData(String email, boolean useCaseFailed) {
        this.email = email;
        this.useCaseFailed = useCaseFailed;
    }

    public String getEmail() {
        return email;
    }

}

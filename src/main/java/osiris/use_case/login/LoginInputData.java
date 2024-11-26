package osiris.use_case.login;

/**
 * The Input Data for the Login Use Case.
 */
public class LoginInputData {

    private final String email;
    private final String password;
    private final String access_code;

    public LoginInputData(String email, String password, String access_code) {
        this.email = email;
        this.password = password;
        this.access_code = access_code;
    }

    String getEmail() {
        return email;
    }

    String getPassword() {
        return password;
    }

    String getAccessCode() {
        return access_code;
    }

}

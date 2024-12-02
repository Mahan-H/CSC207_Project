package osiris.use_case.login;

/**
 * The Input Data for the Login Use Case.
 */
public class LoginInputData {

    private final String user;
    private final String password;
    private final String access_code;

    public LoginInputData(String user, String password, String access_code) {
        this.user = user;
        this.password = password;
        this.access_code = access_code;
    }

    String getUser() {
        return user;
    }

    String getPassword() {
        return password;
    }

    String getAccessCode() {
        return access_code;
    }

}

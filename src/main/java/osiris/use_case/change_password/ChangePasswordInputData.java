package osiris.use_case.change_password;

/**
 * The input data for the Change Password Use Case.
 */
public class ChangePasswordInputData {

    private final String password;
    private final String email;
    private final String access_code;

    public ChangePasswordInputData(String password, String username, String access_code) {
        this.password = password;
        this.email = username;
        this.access_code = access_code;
    }

    String getPassword() {
        return password;
    }

    String getEmail() {
        return email;
    }

    String getAccessCode() {
        return access_code;
    }

}

package osiris.interface_adapter.signup;

/**
 * The state for the Signup View Model.
 */
public class SignupState {
    private String user = "";
    private String userError;
    private String password = "";
    private String passwordError;
    private String repeatPassword = "";
    private String repeatPasswordError;
    private String access_code = "";

    public String getUser() {
        return user;
    }

    public String getUserError() {
        return userError;
    }

    public String getPassword() {
        return password;
    }

    public String getAccessCode() {
        return access_code;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public String getRepeatPasswordError() {
        return repeatPasswordError;
    }

    public void setUser(String email) {
        this.user = email;
    }

    public void setUserError(String userError) {
        this.userError = userError;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public void setRepeatPasswordError(String repeatPasswordError) {
        this.repeatPasswordError = repeatPasswordError;
    }

    @Override
    public String toString() {
        return "SignupState{"
                + "user='" + user + '\''
                + ", password='" + password + '\''
                + ", repeatPassword='" + repeatPassword + '\''
                + ", access_code='" + access_code + '\''
                + '}';
    }
}

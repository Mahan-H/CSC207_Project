package osiris.interface_adapter.login;

/**
 * The state for the Login View Model.
 */
public class LoginState {
    private String email = "";
    private String loginError;
    private String password = "";
    private String access_code = "";

    public String getEmail() {
        return email;
    }

    public String getLoginError() {
        return loginError;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLoginError(String usernameError) {
        this.loginError = usernameError;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccessCode(String s) {
    }

    public String getAccessCode() {
        return access_code;
    }

    public void setAccess_code(String access_code) {
        this.access_code = access_code;
    }
}

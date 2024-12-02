package osiris.interface_adapter.login;

/**
 * The state for the Login View Model.
 */
public class LoginState {
    private String user = "";
    private String loginError;
    private String password = "";
    private String access_code = "";

    public String getUser() {
        return user;
    }

    public String getLoginError() {
        return loginError;
    }

    public String getPassword() {
        return password;
    }

    public void setUser(String user) {
        this.user = user;
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

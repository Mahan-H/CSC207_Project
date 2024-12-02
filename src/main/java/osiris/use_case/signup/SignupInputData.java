package osiris.use_case.signup;

/**
 * The Input Data for the Signup Use Case.
 */
public class SignupInputData {

    private final String email;
    private final String password;
    private final String repeatPassword;
    private final String accessCode;

    public SignupInputData(String email, String password, String repeatPassword, String accessCode) {
        this.email = email;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.accessCode = accessCode;
    }

    String getEmail() {
        return email;
    }

    String getPassword() {
        return password;
    }

    String getAccessCode() {
        return accessCode;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }
}

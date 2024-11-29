package osiris.use_case.signup;

/**
 * Output Data for the Signup Use Case.
 */
public class SignupOutputData {

    private final String email;

    private final boolean useCaseFailed;

    public SignupOutputData(String email, boolean useCaseFailed) {
        this.email = email;
        this.useCaseFailed = useCaseFailed;
    }

    public String getEmail() {
        return email;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}

package osiris.use_case.signup;

/**
 * Output Data for the Signup Use Case.
 */
public class SignupOutputData {

    private final String user;

    private final boolean useCaseFailed;

    public SignupOutputData(String user, boolean useCaseFailed) {
        this.user = user;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUser() {
        return user;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}

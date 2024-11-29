package osiris.use_case.logout;

/**
 * Output Data for the Logout Use Case.
 */
public class LogoutOutputData {

    private String email;
    private boolean useCaseFailed;

    public LogoutOutputData(String email, boolean useCaseFailed) {
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

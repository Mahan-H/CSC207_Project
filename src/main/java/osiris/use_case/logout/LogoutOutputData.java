package osiris.use_case.logout;

/**
 * Output Data for the Logout Use Case.
 */
public class LogoutOutputData {

    private String user;
    private boolean useCaseFailed;

    public LogoutOutputData(String user, boolean useCaseFailed) {
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

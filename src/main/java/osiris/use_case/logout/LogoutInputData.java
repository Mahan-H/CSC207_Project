package osiris.use_case.logout;

/**
 * The Input Data for the Logout Use Case.
 */
public class LogoutInputData {
    private final String user;

    public LogoutInputData(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

}

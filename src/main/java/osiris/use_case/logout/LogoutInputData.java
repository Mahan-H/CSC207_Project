package osiris.use_case.logout;

/**
 * The Input Data for the Logout Use Case.
 */
public class LogoutInputData {
    private final String email;

    public LogoutInputData(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

}

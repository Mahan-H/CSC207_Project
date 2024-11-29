package osiris.use_case.change_password;

/**
 * Output Data for the Change Password Use Case.
 */
public class ChangePasswordOutputData {

    private final String email;

    private final boolean useCaseFailed;

    public ChangePasswordOutputData(String email, boolean useCaseFailed) {
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

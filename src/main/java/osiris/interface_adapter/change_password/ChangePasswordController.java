package osiris.interface_adapter.change_password;

import osiris.use_case.change_password.ChangePasswordInputBoundary;
import osiris.use_case.change_password.ChangePasswordInputData;

/**
 * Controller for the Change Password Use Case.
 */
public class ChangePasswordController {
    private final ChangePasswordInputBoundary userChangePasswordUseCaseInteractor;

    public ChangePasswordController(ChangePasswordInputBoundary userChangePasswordUseCaseInteractor) {
        this.userChangePasswordUseCaseInteractor = userChangePasswordUseCaseInteractor;
    }

    /**
     * Executes the Change Password Use Case.
     * @param password the new password
     * @param username the user whose password to change
     */
    public void execute(String password, String username, String access_code) {
        final ChangePasswordInputData changePasswordInputData = new ChangePasswordInputData(username, password, access_code);

        userChangePasswordUseCaseInteractor.execute(changePasswordInputData);
    }
}

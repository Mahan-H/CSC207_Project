package osiris.interface_adapter.verify;

import osiris.use_case.verify.VerifyInputBoundary;
import osiris.use_case.verify.VerifyInputData;

/**
 * Controller for the Verify Use Case.
 */

public class VerifyController {
    private final VerifyInputBoundary verifyInteractor;

    public VerifyController(VerifyInputBoundary verifyInteractor) {
        this.verifyInteractor = verifyInteractor;
    }

    public String generateCaptcha() {
        // Generate a new CAPTCHA via the interactor
        String sessionId = "user-session-id";
        return verifyInteractor.generateCaptcha(sessionId);
    }

    /**
     * Validates the entered CAPTCHA against the current CAPTCHA.
     *
     * @param enteredCaptcha the CAPTCHA entered by the user
     * @param currentCaptcha the CAPTCHA currently displayed to the user
     * @return true if the entered CAPTCHA matches the current CAPTCHA; false otherwise
     */
    public boolean validateCaptcha(String enteredCaptcha, String currentCaptcha) {
        final String sessionId = "user-session-id";
        return verifyInteractor.validateCaptcha(enteredCaptcha, currentCaptcha);
    }

    /**
     * Switches the view to the Dashboard view upon successful verification.
     * This method interacts with the interactor to trigger the transition to the Dashboard view.
     */
    public void switchToDashboardView() {
        verifyInteractor.switchToDashboardView();
    }

    /**
     * Executes the CAPTCHA verification process.
     *
     * @param enteredCaptcha the CAPTCHA entered by the user
     * This method creates a {@link VerifyInputData} object with the entered CAPTCHA
     * and passes it to the interactor for processing.
     */
    public void execute(String enteredCaptcha) {
        final VerifyInputData inputData = new VerifyInputData("User", enteredCaptcha);
        verifyInteractor.execute(inputData);
    }
}

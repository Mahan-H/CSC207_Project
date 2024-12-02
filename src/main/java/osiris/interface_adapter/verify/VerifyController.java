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

    public boolean validateCaptcha(String enteredCaptcha, String currentCaptcha) {
        String sessionId = "user-session-id";
        return verifyInteractor.validateCaptcha(enteredCaptcha, currentCaptcha);
    }

    public void execute(String enteredCaptcha) {
        VerifyInputData inputData = new VerifyInputData("User", enteredCaptcha);
        verifyInteractor.execute(inputData);
    }
}

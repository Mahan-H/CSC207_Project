package osiris.use_case.verify;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class VerifyInteractor implements VerifyInputBoundary {
    private final VerifyOutputBoundary outputBoundary;
    private final Map<String, String> captchaStore;

    public VerifyInteractor(VerifyOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
        this.captchaStore = new HashMap<>();
    }

    @Override
    public void execute(VerifyInputData inputData) {
        String user = inputData.getUser();
        String inputCaptcha = inputData.getCaptchaResponse();
        String storedCaptcha = captchaStore.get(user);

        if (storedCaptcha != null && storedCaptcha.equalsIgnoreCase(inputCaptcha)) {
            outputBoundary.prepareSuccessView(new VerifyOutputData(user, false, storedCaptcha));
        }
        else {
            outputBoundary.prepareFailView("Invalid CAPTCHA. Please try again.");
        }
    }

    @Override
    public void switchToSignUpView() {
        outputBoundary.switchToSignUpView();
    }

    @Override
    public boolean validateCaptcha(String userInputCaptcha, String storedCaptcha) {
        return userInputCaptcha != null && userInputCaptcha.equals(storedCaptcha);
    }

    public String generateCaptcha(String sessionId) {
        String captchaCode = generateRandomCode(6);
        captchaStore.put(sessionId, captchaCode);
        return captchaCode;
    }

    private String generateRandomCode(int length) {
        final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom random = new SecureRandom();
        StringBuilder code = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            code.append(characters.charAt(index));
        }

        return code.toString();
    }

    /**
     * Method to switch to the Dashboard view after successful CAPTCHA verification.
     */
    public void switchToDashboardView() {
        outputBoundary.switchToDashboardView();
    }
}


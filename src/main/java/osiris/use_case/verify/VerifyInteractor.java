package osiris.use_case.verify;

import java.security.SecureRandom;

import osiris.data_access.DBUserDataAccessObject;

/**
 * The Verify Email Interactor.
 */

public class VerifyInteractor implements VerifyInputBoundary {
    private final VerifyOutputBoundary userPresenter;
    private final interface_adapter.EmailService emailService;
    private final DBUserDataAccessObject dataAccessObject;

    public VerifyInteractor(VerifyOutputBoundary verifyOutputBoundary, interface_adapter.EmailService emailService,
                            DBUserDataAccessObject dataAccessObject) {
        this.userPresenter = verifyOutputBoundary;
        this.emailService = emailService;
        this.dataAccessObject = dataAccessObject;
    }

    @Override
    public void execute(VerifyInputData verifyInputData) {
        final String email = verifyInputData.getEmail();
        final String inputCode = verifyInputData.getVerifyCode();
        final String storedCode = dataAccessObject.getVerificationCode(email);
        if (storedCode != null && storedCode.equals(inputCode)) {
            userPresenter.prepareSuccessView(new VerifyOutputData(email, false));
        }
        else {
            userPresenter.prepareFailView("Invalid verification code.");
        }
    }

    @Override
    public void switchToSignUpView() {
        userPresenter.switchToSignUpView();
    }

    @Override
    public void resendVerificationEmail(VerifyInputData inputData) {
        final String email = inputData.getEmail();
        final String newVerificationCode = generateVerificationCode(6);

        dataAccessObject.saveVerificationCode(email, newVerificationCode);

        emailService.sendVerificationEmail(email, "Resend Email Verification", "Your new verification code is: "
                + newVerificationCode);
    }

    private String generateVerificationCode(int length) {
        final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        final SecureRandom random = new SecureRandom();
        final StringBuilder code = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            final int index = random.nextInt(characters.length());
            code.append(characters.charAt(index));
        }
        return code.toString();
    }
}

package use_case.verify;

import entity.User;
import interface_adapter.EmailService;

import java.security.SecureRandom;

/**
 * The Verify Email Interactor.
 */

public class VerifyInteractor implements VerifyInputBoundary {
    private final VerifyOutputBoundary userPresenter;
    private final EmailService emailService;

    public VerifyInteractor(VerifyOutputBoundary verifyOutputBoundary, EmailService emailService) {
        this.userPresenter = verifyOutputBoundary;
        this.emailService = emailService;
    }

    @Override
    public void execute(VerifyInputData verifyInputData) {
        final VerifyOutputData verifyOutputData = new VerifyOutputData(verifyInputData.getVerifyCode(), false);
        userPresenter.prepareSuccessView(verifyOutputData);
    }

    @Override
    public void switchToSignUpView() {
        userPresenter.switchToSignUpView();
    }

    @Override
    public void resendVerificationEmail(VerifyInputData inputData) {
        String email = inputData.getUsername();
        String newVerificationCode = generateVerificationCode(6);
        emailService.sendEmail(email, "Resend Email Verification", "Your new verification code is: " + newVerificationCode);
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

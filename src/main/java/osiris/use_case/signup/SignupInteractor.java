package osiris.use_case.signup;

import osiris.data_access.EmailServiceImpl;
import osiris.entity.User;
import osiris.entity.UserFactory;

import java.security.SecureRandom;

/**
 * The Signup Interactor.
 */
public class SignupInteractor implements SignupInputBoundary {
    private final SignupUserDataAccessInterface userDataAccessObject;
    private final SignupOutputBoundary userPresenter;
    private final UserFactory userFactory;
    private final EmailServiceImpl emailService;

    public SignupInteractor(SignupUserDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory,
                            EmailServiceImpl emailService) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
        this.emailService = emailService;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        if (userDataAccessObject.existsByName(signupInputData.getEmail())) {
            userPresenter.prepareFailView("User already exists.");
        }
        else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        }
        else {
            final User user = userFactory.create(signupInputData.getEmail(), signupInputData.getPassword(),
                    signupInputData.getAccessCode());
            userDataAccessObject.save(user);
            final String verificationCode = generateVerificationCode(6);
            emailService.sendVerificationEmail(user.getEmail(), "Osiris Verification Code", verificationCode);
            final SignupOutputData signupOutputData = new SignupOutputData(user.getEmail(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
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

    @Override
    public void switchToVerifyView() {
        userPresenter.switchToVerifyView();
    }

    @Override
    public void switchToWelcomeView() {
        userPresenter.switchToWelcomeView();
    }
}

package osiris.use_case.signup;

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

    // Field to store the generated CAPTCHA
    private String generatedCaptcha;

    public SignupInteractor(SignupUserDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        if (userDataAccessObject.existsByName(signupInputData.getUser())) {
            userPresenter.prepareFailView("User already exists.");
        } else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        } else {
            // Store user information for verification
            User user = userFactory.create(signupInputData.getUser(), signupInputData.getPassword(), null);
            userDataAccessObject.save(user);

            // Pass control to verification step
            userPresenter.switchToVerifyView();
        }
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

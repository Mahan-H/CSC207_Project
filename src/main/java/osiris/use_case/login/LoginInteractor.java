package osiris.use_case.login;

import osiris.entity.User;
import osiris.entity.UserFactory;
import osiris.use_case.plaid.PlaidDataBaseUserAccessObjectInterface;
import osiris.use_case.plaid.UserPlaidDataAccessInterface;

import java.awt.*;
import java.net.URI;
import java.net.URLEncoder;

/**
 * The Login Interactor.
 */
public class LoginInteractor implements LoginInputBoundary {
    private final LoginUserDataAccessInterface userDataAccessObject;
    private final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary, UserFactory userFactory,
                           UserPlaidDataAccessInterface plaidDataAccessObject,
                           PlaidDataBaseUserAccessObjectInterface plaidDataBaseUserAccessObjectInterface) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        final String username = loginInputData.getUser();
        final String password = loginInputData.getPassword();
        if (!userDataAccessObject.existsByName(username)) {
            loginPresenter.prepareFailView(username + ": Account does not exist.");
        }
        else {
            final String pwd = userDataAccessObject.get(username).getPassword();
            if (!password.equals(pwd)) {
                loginPresenter.prepareFailView("Incorrect password for \"" + username + "\".");
            }
            else {
                final User user = userDataAccessObject.get(loginInputData.getUser());

                userDataAccessObject.setCurrentUser(user.getUser());
                final LoginOutputData loginOutputData = new LoginOutputData(user.getUser(), false);
                try {
                    final String encodedEmail = URLEncoder.encode(user.getUser(), "UTF-8");
                    final String url = "http://localhost:8080?email=" + encodedEmail;
                    Desktop.getDesktop().browse(new URI(url));
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                loginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }

    @Override
    public void switchToWelcomeView() {
        loginPresenter.switchToWelcomeView();
    }
}

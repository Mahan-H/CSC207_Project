package osiris.interface_adapter.login;

import osiris.interface_adapter.ViewManagerModel;
import osiris.interface_adapter.verify.VerifyState;
import osiris.interface_adapter.verify.VerifyViewModel;
import osiris.interface_adapter.welcome.WelcomeViewModel;
import osiris.use_case.login.LoginOutputBoundary;
import osiris.use_case.login.LoginOutputData;

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final VerifyViewModel verifyViewModel;
    private final ViewManagerModel viewManagerModel;
    private final WelcomeViewModel welcomeViewModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          LoginViewModel loginViewModel, WelcomeViewModel welcomeViewModel,
                          VerifyViewModel verifyViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.verifyViewModel = verifyViewModel;
        this.loginViewModel = loginViewModel;
        this.welcomeViewModel = welcomeViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the logged in view.
        final VerifyState verifyState = verifyViewModel.getState();
        verifyState.setUsername(response.getEmail());
        this.verifyViewModel.setState(verifyState);
        this.verifyViewModel.firePropertyChanged();
        this.viewManagerModel.setState(verifyViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final LoginState loginState = loginViewModel.getState();
        loginState.setLoginError(error);
        loginViewModel.firePropertyChanged();
    }

    @Override
    public void switchToWelcomeView() {
        viewManagerModel.setState(welcomeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}

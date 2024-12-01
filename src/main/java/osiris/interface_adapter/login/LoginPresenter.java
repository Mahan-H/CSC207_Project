package osiris.interface_adapter.login;

import osiris.interface_adapter.ViewManagerModel;
import osiris.interface_adapter.change_password.LoggedInState;
import osiris.interface_adapter.change_password.LoggedInViewModel;
import osiris.interface_adapter.dashboard.DashboardViewModel;
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
   // private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;
    private final WelcomeViewModel welcomeViewModel;
    private final DashboardViewModel dashboardViewModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          LoginViewModel loginViewModel, WelcomeViewModel welcomeViewModel,
                          VerifyViewModel verifyViewModel, DashboardViewModel dashboardViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.verifyViewModel = verifyViewModel;
        this.loginViewModel = loginViewModel;
        this.welcomeViewModel = welcomeViewModel;
        this.dashboardViewModel = dashboardViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the logged in view.

        /**
        final VerifyState verifyState = verifyViewModel.getState();
        verifyState.setUsername(response.getEmail());
        this.verifyViewModel.setState(verifyState);
        this.verifyViewModel.firePropertyChanged();
        this.viewManagerModel.setState(verifyViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
         **/

        viewManagerModel.setState(dashboardViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
         /**
        final LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setUsername(response.getUsername());
        this.loggedInViewModel.setState(loggedInState);
        this.loggedInViewModel.firePropertyChanged();

        this.viewManagerModel.setState(loggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
         **/

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

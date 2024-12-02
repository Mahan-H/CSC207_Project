package osiris.interface_adapter.logout;

import osiris.interface_adapter.ViewManagerModel;
import osiris.interface_adapter.change_password.LoggedInState;
import osiris.interface_adapter.change_password.LoggedInViewModel;
import osiris.interface_adapter.login.LoginState;
import osiris.interface_adapter.login.LoginViewModel;
import osiris.use_case.logout.LogoutOutputBoundary;
import osiris.use_case.logout.LogoutOutputData;

/**
 * The Presenter for the Logout Use Case.
 */
public class LogoutPresenter implements LogoutOutputBoundary {

    private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;

    public LogoutPresenter(ViewManagerModel viewManagerModel,
                           LoggedInViewModel loggedInViewModel,
                           LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(LogoutOutputData response) {
        final LoggedInState state = loggedInViewModel.getState();
        state.setUser("");
        loggedInViewModel.setState(state);
        loggedInViewModel.firePropertyChanged();

        final LoginState loginState = loginViewModel.getState();
        loginState.setUser("");
        loginState.setPassword("");
        loginState.setAccessCode("");
        loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        // This code tells the View Manager to switch to the LoginView.
        this.viewManagerModel.setState(loginViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        // No need to add code here. We'll assume that logout can't fail.
        // Thought question: is this a reasonable assumption?
    }
}

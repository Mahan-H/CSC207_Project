package osiris.interface_adapter.welcome;

import osiris.interface_adapter.ViewManagerModel;
import osiris.interface_adapter.login.LoginViewModel;
import osiris.interface_adapter.signup.SignupViewModel;
import osiris.use_case.welcome.WelcomeOutputBoundary;

/**
 * Presenter for the Welcome Use Case.
 */
public class WelcomePresenter implements WelcomeOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;
    private final SignupViewModel signupViewModel;
    private final WelcomeViewModel welcomeViewModel;

    public WelcomePresenter(ViewManagerModel viewManagerModel,
                            WelcomeViewModel welcomeViewModel,
                            LoginViewModel loginViewModel, SignupViewModel signupViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.welcomeViewModel = welcomeViewModel;
        this.loginViewModel = loginViewModel;
        this.signupViewModel = signupViewModel;
    }

    @Override
    public void switchToLoginView() {
        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToSignUpView() {
        viewManagerModel.setState(signupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}

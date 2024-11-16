package interface_adapter.signup;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.verify.VerifyState;
import interface_adapter.verify.VerifyViewModel;

import interface_adapter.welcome.WelcomeViewModel;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;

/**
 * The Presenter for the Signup Use Case.
 */
public class SignupPresenter implements SignupOutputBoundary {

    private final SignupViewModel signupViewModel;
    private final VerifyViewModel verifyViewModel;
    private final ViewManagerModel viewManagerModel;
    private final WelcomeViewModel welcomeViewModel;

    public SignupPresenter(ViewManagerModel viewManagerModel,
                           SignupViewModel signupViewModel,
                           VerifyViewModel verifyViewModel, WelcomeViewModel welcomeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.verifyViewModel = verifyViewModel;
        this.welcomeViewModel = welcomeViewModel;
    }

    @Override
    public void prepareSuccessView(SignupOutputData response) {
        // On success, switch to the login view.
        final VerifyState verifyState = verifyViewModel.getState();
        verifyState.setUsername(response.getEmail());
        this.verifyViewModel.setState(verifyState);
        verifyViewModel.firePropertyChanged();

        viewManagerModel.setState(verifyViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final SignupState signupState = signupViewModel.getState();
        signupState.setEmailError(error);
        signupViewModel.firePropertyChanged();
    }

    @Override
    public void switchToVerifyView() {
        viewManagerModel.setState(verifyViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToWelcomeView() {
        viewManagerModel.setState(welcomeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}

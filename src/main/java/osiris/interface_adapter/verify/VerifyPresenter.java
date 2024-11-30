package osiris.interface_adapter.verify;

import osiris.interface_adapter.ViewManagerModel;
import osiris.interface_adapter.signup.SignupViewModel;
import osiris.use_case.verify.VerifyOutputBoundary;
import osiris.use_case.verify.VerifyOutputData;
/**
 * The Presenter for the Verify Email Use Case.
 */

public class VerifyPresenter implements VerifyOutputBoundary {
    private final VerifyViewModel verifyViewModel;
    private final SignupViewModel signupViewModel;
    private final ViewManagerModel viewManagerModel;

    public VerifyPresenter(VerifyViewModel verifyViewModel, SignupViewModel signupViewModel,
                           ViewManagerModel viewManagerModel) {
        this.verifyViewModel = verifyViewModel;
        this.signupViewModel = signupViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(VerifyOutputData outputData) {
        // currently there isn't anything to change based on the output data,
        // since the output data only contains the username, which remains the same.
        // We still fire the property changed event, but just to let the view know that
        // it can alert the user that their password was changed successfully...
        verifyViewModel.firePropertyChanged("password");
    }

    @Override
    public void prepareFailView(String error) {
        // note: this use case currently can't fail
        // If we get the wrong code
    }

    @Override
    public void switchToSignUpView() {
        viewManagerModel.setState(signupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}

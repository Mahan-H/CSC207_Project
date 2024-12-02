package osiris.interface_adapter.verify;

import osiris.interface_adapter.ViewModel;

/**
 * The View Model for the Logged In View.
 */

public class VerifyViewModel extends ViewModel<VerifyState> {
    public static final String TITLE_LABEL = "Verify Your Account";
    public static final String VERIFY_LABEL = "Enter the Code: ";
    public static final String VERIFY_BUTTON_LABEL = "Verify";
    public static final String RETURN_BUTTON_LABEL = "Go Back";
    private String captcha;

    public VerifyViewModel() {
        super("verify");
        setState(new VerifyState());
    }

    /**
     * Returns the current state of the VerifyViewModel.
     */
    @Override
    public VerifyState getState() {
        return super.getState();
    }

    /**
     * Sets the state of the VerifyViewModel.
     *
     * @param state The new state to set
     */
    @Override
    public void setState(VerifyState state) {
        super.setState(state);
    }

    /**
     * Notifies listeners that the specified property has changed.
     *
     * @param propertyName The name of the property that changed
     */
    @Override
    public void firePropertyChanged(String propertyName) {
        super.firePropertyChanged(propertyName);
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
        firePropertyChanged("captcha");
    }
}

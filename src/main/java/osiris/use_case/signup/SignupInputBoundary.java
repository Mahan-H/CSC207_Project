package osiris.use_case.signup;

/**
 * Input Boundary for actions which are related to signing up.
 */
public interface SignupInputBoundary {

    /**
     * Executes the signup use case.
     * @param signupInputData the input data
     */
    void execute(SignupInputData signupInputData);

    /**
     * Executes the switch to verify view use case.
     */
    void switchToVerifyView();

    /**
     * Executes the switch to welcome view use case.
     */
    void switchToWelcomeView();
}

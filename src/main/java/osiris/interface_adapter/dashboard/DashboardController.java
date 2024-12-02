package osiris.interface_adapter.dashboard;

import osiris.use_case.dashboard.DashboardInputBoundary;

/**
 * Controller for the Dashboard Use Case.
 */
public class DashboardController {

    private final DashboardInputBoundary userDashboardUseCaseInteractor;

    public DashboardController(DashboardInputBoundary userDashboardUseCaseInteractor) {
        this.userDashboardUseCaseInteractor = userDashboardUseCaseInteractor;
    }

    /**
     * Executes the "switch to addBankAccountView" Use Case.
     */
    public void switchToLoginView() {
        userDashboardUseCaseInteractor.switchToAddBankAccountView();
    }

    /**
     * Executes the "switch to ViewExpenses" Use Case.
     */
    public void switchToViewExpenses() {
        userDashboardUseCaseInteractor.switchToViewExpenses();
    }
}

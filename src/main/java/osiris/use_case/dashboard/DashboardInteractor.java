package osiris.use_case.dashboard;

import osiris.entity.UserFactory;

/**
 * Interactor for the Dashboard Use Case.
 */
public class DashboardInteractor implements DashboardInputBoundary {

    private final UserFactory userFactory;
    private final DashboardOutputBoundary userPresenter;

    public DashboardInteractor(DashboardOutputBoundary dashboardOutputBoundary, UserFactory userFactory) {
        this.userPresenter = dashboardOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void switchToAddBankAccountView() {
        userPresenter.switchToAddBankAccountView();
    }

    @Override
    public void switchToViewExpenses() {
        userPresenter.switchToViewExpenses();
    }

    @Override
    public void switchToBudgetView() { userPresenter.switchToBudgetView(); }
}

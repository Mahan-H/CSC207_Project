package osiris.interface_adapter.dashboard;

import osiris.interface_adapter.ViewManagerModel;
import osiris.interface_adapter.viewexpenses.ViewExpensesViewModel;
import osiris.use_case.dashboard.DashboardOutputBoundary;

/**
 * Presenter for the Welcome Use Case.
 */
public class DashboardPresenter implements DashboardOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final DashboardViewModel dashboardViewModel;
    private final ViewExpensesViewModel viewExpensesViewModel;

    public DashboardPresenter(ViewManagerModel viewManagerModel,
                              DashboardViewModel dashboardViewModel, ViewExpensesViewModel viewExpensesViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.dashboardViewModel = dashboardViewModel;
        this.viewExpensesViewModel = viewExpensesViewModel;
    }

    @Override
    public void switchToAddBankAccountView() {

    }

    @Override
    public void switchToViewExpenses() {
        viewManagerModel.setState("view expenses");
        viewManagerModel.firePropertyChanged();
    }

}

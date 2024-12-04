package osiris.interface_adapter.dashboard;

import osiris.interface_adapter.ViewManagerModel;
import osiris.interface_adapter.budget.BudgetViewModel;
import osiris.interface_adapter.viewexpenses.ViewExpensesViewModel;
import osiris.use_case.dashboard.DashboardOutputBoundary;

/**
 * Presenter for the Welcome Use Case.
 */
public class DashboardPresenter implements DashboardOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final DashboardViewModel dashboardViewModel;
    private final ViewExpensesViewModel viewExpensesViewModel;
    private final BudgetViewModel budgetViewModel;

    public DashboardPresenter(ViewManagerModel viewManagerModel,
                              DashboardViewModel dashboardViewModel, ViewExpensesViewModel viewExpensesViewModel,
                              BudgetViewModel budgetViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.dashboardViewModel = dashboardViewModel;
        this.viewExpensesViewModel = viewExpensesViewModel;
        this.budgetViewModel = budgetViewModel;
    }

    @Override
    public void switchToAddBankAccountView() {

    }

    @Override
    public void switchToViewExpenses() {
        viewManagerModel.setState("view expenses");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToBudgetView() {
        viewManagerModel.setState("budget");
        viewManagerModel.firePropertyChanged();
    }

}

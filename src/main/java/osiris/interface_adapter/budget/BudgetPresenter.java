package osiris.interface_adapter.budget;

import osiris.interface_adapter.ViewManagerModel;
import osiris.interface_adapter.dashboard.DashboardViewModel;
import osiris.use_case.budget.BudgetOutputBoundary;

/**
 * The Presenter for the budget Use Case.
 */
public class BudgetPresenter implements BudgetOutputBoundary {
    private final BudgetViewModel budgetViewModel;
    private final ViewManagerModel viewManagerModel;
    private DashboardViewModel dashboardViewModel;

    public BudgetPresenter(BudgetViewModel budgetViewModel,
                           ViewManagerModel viewManagerModel) {
        this.budgetViewModel = budgetViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void switchToDashboardView() {
        viewManagerModel.setState("dashboard");
        viewManagerModel.firePropertyChanged();
    }

}

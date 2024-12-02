package osiris.interface_adapter.budgetamount;

import osiris.interface_adapter.ViewManagerModel;
import osiris.interface_adapter.budget.BudgetViewModel;
import osiris.interface_adapter.welcome.WelcomeViewModel;
import osiris.use_case.budgetamount.BudgetAmountOutputBoundary;
import osiris.use_case.budgetamount.BudgetAmountOutputData;

/**
 * The Presenter for the BudgetAmount Use Case.
 */
public class BudgetAmountPresenter implements BudgetAmountOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final WelcomeViewModel welcomeViewModel;
    private final BudgetViewModel budgetViewModel;

    public BudgetAmountPresenter(ViewManagerModel viewManagerModel, WelcomeViewModel welcomeViewModel,
                                 BudgetViewModel budgetViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.welcomeViewModel = welcomeViewModel;
        this.budgetViewModel = budgetViewModel;
    }

    public void prepareSuccessView(BudgetAmountOutputBoundary response) {
        viewManagerModel.setState(budgetViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareSuccessView(BudgetAmountOutputData outputData) {

    }

    @Override
    public void switchToDashboardView() {
        viewManagerModel.setState(welcomeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToBudgetView() {

    }

}

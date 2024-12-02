package osiris.interface_adapter.viewexpenses;

import osiris.interface_adapter.ViewManagerModel;
import osiris.interface_adapter.dashboard.DashboardViewModel;
import osiris.use_case.viewexpenses.ViewExpensesOutputBoundary;
import osiris.use_case.viewexpenses.ViewExpensesOutputData;

/**
 * The Presenter for the ViewExpenses Use Case.
 */
public class ViewExpensesPresenter implements ViewExpensesOutputBoundary {
    private final ViewExpensesViewModel viewExpensesViewModel;
    private final ViewManagerModel viewManagerModel;
    private DashboardViewModel dashboardViewModel;

    public ViewExpensesPresenter(ViewExpensesViewModel viewExpensesViewModel,
                                 ViewManagerModel viewManagerModel) {
        this.viewExpensesViewModel = viewExpensesViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareChart(ViewExpensesOutputData outputData) {
        final ViewExpensesState viewExpensesState = viewExpensesViewModel.getState();
        viewExpensesState.setEssential(outputData.getEssentialTotal());
        viewExpensesState.setNonEssential(outputData.getNonEssentialTotal());
        viewExpensesViewModel.setState(viewExpensesState);
        viewManagerModel.setState(viewExpensesViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
        // System.out.println("Essential Amount: " + outputData.getEssentialTotal());
        // System.out.println("Non-Essential Amount: " + outputData.getNonEssentialTotal());
    }

    @Override
    public void switchToHomeView() {
        viewManagerModel.setState("dashboard");
        viewManagerModel.firePropertyChanged();
    }

}

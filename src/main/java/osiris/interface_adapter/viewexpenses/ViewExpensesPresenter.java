package osiris.interface_adapter.viewexpenses;

import osiris.interface_adapter.ViewManagerModel;
import osiris.use_case.viewexpenses.ViewExpensesOutputBoundary;
import osiris.use_case.viewexpenses.ViewExpensesOutputData;

/**
 * The Presenter for the ViewExpenses Use Case.
 */
public class ViewExpensesPresenter implements ViewExpensesOutputBoundary {
    private final ViewExpensesViewModel viewExpensesViewModel;
    private final ViewManagerModel viewManagerModel;
    //    HomeViewModel homeViewModel;

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
    }

    @Override
    public void switchToHomeView() {
    }

}

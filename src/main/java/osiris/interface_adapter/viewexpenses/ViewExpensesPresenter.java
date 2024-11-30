package osiris.interface_adapter.viewexpenses;

import osiris.interface_adapter.ViewManagerModel;
import osiris.use_case.viewexpenses.ViewExpensesOutputBoundary;
import osiris.use_case.viewexpenses.ViewExpensesOutputData;

/**
 * The Presenter for the ViewExpenses Use Case.
 */
public class ViewExpensesPresenter implements ViewExpensesOutputBoundary {
    private final ViewExpensesViewModel viewExpensesViewModel;
    private final ViewExpensesState viewExpensesState;
    private final ViewManagerModel viewManagerModel;
    //    HomeViewModel homeViewModel;

    public ViewExpensesPresenter(ViewExpensesViewModel viewExpensesViewModel, ViewExpensesState viewExpensesState,
                                 ViewManagerModel viewManagerModel) {
        this.viewExpensesViewModel = viewExpensesViewModel;
        this.viewExpensesState = viewExpensesState;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareChart(ViewExpensesOutputData outputData) {
        viewManagerModel.setState(viewExpensesViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToHomeView() {
    }

}

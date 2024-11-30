package osiris.interface_adapter.viewexpenses;


import osiris.interface_adapter.ViewManagerModel;
import osiris.use_case.viewexpenses.ViewExpensesOutputBoundary;
import osiris.use_case.viewexpenses.ViewExpensesOutputData;
import osiris.interface_adapter.viewexpenses.ViewExpensesViewModel;
import osiris.interface_adapter.viewexpenses.ViewExpensesState;


public class ViewExpensesPresenter implements ViewExpensesOutputBoundary {
    private final ViewExpensesViewModel viewExpensesViewModel;
    private final ViewExpensesState viewExpensesState;
//    HomeViewModel homeViewModel;
    private final ViewManagerModel viewManagerModel;


    public ViewExpensesPresenter(ViewExpensesViewModel viewExpensesViewModel, ViewExpensesState viewExpensesState, ViewManagerModel viewManagerModel) {
        this.viewExpensesViewModel = viewExpensesViewModel;
        this.viewExpensesState = viewExpensesState;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void PrepareChart(ViewExpensesOutputData outputData) {
        final ViewExpensesState state = viewExpensesState;

        state.setEssential(outputData.getEssentialTotal());
        state.setNonEssential(outputData.getNonEssentialTotal());

        viewManagerModel.setState(viewExpensesViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToHomeView() {

    }


}

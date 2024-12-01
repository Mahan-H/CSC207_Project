package osiris.interface_adapter.dashboard;

import osiris.interface_adapter.ViewManagerModel;
import osiris.use_case.dashboard.DashboardOutputBoundary;

/**
 * Presenter for the Welcome Use Case.
 */
public class DashboardPresenter implements DashboardOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final DashboardViewModel dashboardViewModel;

    public DashboardPresenter(ViewManagerModel viewManagerModel,
                              DashboardViewModel dashboardViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.dashboardViewModel = dashboardViewModel;
    }

    @Override
    public void switchToAddBankAccountView() {
//        viewManagerModel.setState(addBankAccountViewModel.getViewName());
//        viewManagerModel.firePropertyChanged();
    }

}

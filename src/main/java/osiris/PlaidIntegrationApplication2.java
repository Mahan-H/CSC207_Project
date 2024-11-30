package osiris;

import osiris.data_access.PlaidDataAccessObject;
import osiris.interface_adapter.viewexpenses.ViewExpensesController;
import osiris.interface_adapter.viewexpenses.ViewExpensesPresenter;
import osiris.use_case.viewexpenses.ViewExpensesInteractor;

public class PlaidIntegrationApplication2 {
    public static void main(String[] args) {
        // Initialize dependencies
        PlaidDataAccessObject dataAccess = new PlaidDataAccessObject();
        ViewExpensesPresenter presenter = new ViewExpensesPresenter();
        ViewExpensesInteractor interactor = new ViewExpensesInteractor(dataAccess, presenter);
        ViewExpensesController controller = new ViewExpensesController(interactor);

        // Example access token
        String accessToken = "your-access-token";

        // Trigger use case
//        controller.viewExpenses(accessToken);
    }
}

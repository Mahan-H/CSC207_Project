package osiris.interface_adapter.viewexpenses;

import osiris.use_case.viewexpenses.ViewExpensesInputBoundary;
import osiris.use_case.viewexpenses.ViewExpensesInputData;

/**
 * The controller for the ViewExpenses Use Case.
 */
public class ViewExpensesController {
    private final ViewExpensesInputBoundary interactor;

    public ViewExpensesController(ViewExpensesInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Executes the ViewExpenses Use Case.
     * @param transactionList List of transactions for a given access token
     */

    public void execute(String transactionList) {
        final ViewExpensesInputData inputData = new ViewExpensesInputData(transactionList);
        interactor.execute(inputData);
    }

    /**
     * Executes the "switch to Home" Use Case.
     */
    public void switchToHomeView() {
        interactor.switchToHomeView();
    }
}

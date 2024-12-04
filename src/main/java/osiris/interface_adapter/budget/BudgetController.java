package osiris.interface_adapter.budget;

import osiris.use_case.budget.BudgetInputBoundary;
import osiris.use_case.budget.BudgetInputData;

/**
 * The controller for the BudgetExpenses Use Case.
 */
public class BudgetController {
    private final BudgetInputBoundary interactor;

    public BudgetController(BudgetInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Executes the budget Use Case.
     * @param transactionList List of transactions for a given access token
     */

    public void execute(String transactionList) {
        final BudgetInputData inputData = new BudgetInputData(transactionList);
        interactor.execute(inputData);
    }

    /**
     * Executes the "switch to Dashboard" Use Case.
     */
    public void switchToHomeView() {
        interactor.switchToDashboardView();
    }
}

package osiris.use_case.budget;

import osiris.use_case.viewexpenses.ViewExpensesInputData;

/**
 * The ImputBoundary for the ViewExpenses.
 */

public interface BudgetInputBoundary {
    /**
     * Processes the input data for viewing expenses.
     *
     * @param inputData Input data containing the required information for the use case.
     */
    void handle(ViewExpensesInputData inputData);

    /**
     * Executes the switch to home view use case.
     */
    void switchToHomeView();
}

package osiris.use_case.viewexpenses;

/**
 * The ImputBoundary for the ViewExpenses.
 */

public interface ViewExpensesInputBoundary {
    /**
     * Processes the input data for viewing expenses.
     *
     * @param inputData Input data containing the required information for the use case.
     */
    void execute(ViewExpensesInputData inputData);

    /**
     * Executes the switch to home view use case.
     */
    void switchToHomeView();
}

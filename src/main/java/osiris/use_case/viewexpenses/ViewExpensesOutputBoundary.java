package osiris.use_case.viewexpenses;

/**
 * ViewExpenses OutBoundary.
 */
public interface ViewExpensesOutputBoundary {
    /**
     * Handles the output data for viewing expenses.
     *
     * @param outputData Output data containing the result of the use case.
     */

    void prepareChart(ViewExpensesOutputData outputData);

    /**
     * Executes the switch to home view use case.
     */
    void switchToHomeView();
}

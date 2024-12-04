package osiris.use_case.budget;


/**
 * The ImputBoundary for the budget.
 */

public interface BudgetInputBoundary {
    /**
     * Processes the input data for budget.
     *
     * @param inputData Input data containing the required information for the use case.
     */
    void execute(BudgetInputData inputData);

    /**
     * Executes the switch to dasboard.
     */
    void switchToDashboardView();
}

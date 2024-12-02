package osiris.use_case.budgetamount;

/**
 * The ImputBoundary for the Budget Amount.
 */

public interface BudgetAmountInputBoundary {
    /**
     * Executes the budget amount use case.
     * @param budgetAmountInputData the input data
     */
    void execute(BudgetAmountInputData budgetAmountInputData);

    /**
     * Executes the switch to budget view use case.
     */
    void switchToBudgetView();

    /**
     * Executes the switch to dashboard view use case.
     */
    void switchToDashboardView();
}

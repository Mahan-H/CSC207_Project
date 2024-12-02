package osiris.use_case.budgetamount;

/**
 * The output boundary for the budget amount Use Case.
 */
public interface BudgetAmountOutputBoundary {
    /**
     * Prepares the success view for the Budget Amount Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(BudgetAmountOutputData outputData);

    void switchToDashboardView();

    void switchToBudgetView();
}

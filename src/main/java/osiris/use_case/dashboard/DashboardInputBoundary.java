package osiris.use_case.dashboard;

/**
 * Input boundary for the Dashboard Use Case.
 */
public interface DashboardInputBoundary {

    /**
     * Executes the switch to add bank account view use case.
     */
    void switchToAddBankAccountView();

    /**
     * Executes the switch to ViewExpenses view use case.
     */
    void switchToViewExpenses();

    /**
     * Executes the switch to budget view use case.
     */
    void switchToBudgetView();
}

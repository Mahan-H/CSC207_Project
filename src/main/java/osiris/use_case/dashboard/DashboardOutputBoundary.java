package osiris.use_case.dashboard;

/**
 * Output boundary for the Dashboard Use Case.
 */
public interface DashboardOutputBoundary {

    /**
     * Switches to the add bank account View.
     */
    void switchToAddBankAccountView();

    /**
     * Switches to the viewExpenses view.
     */
    void switchToViewExpenses();

    /**
     * Switches to the budget View.
     */
    void switchToBudgetView();
}

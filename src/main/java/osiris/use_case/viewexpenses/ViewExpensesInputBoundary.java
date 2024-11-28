package osiris.use_case.viewexpenses;

import osiris.use_case.viewexpenses.ViewExpensesInputData;

public interface ViewExpensesInputBoundary {

    /**
     * Execute the View Expenses Use Case.
     * @param viewExpensesInputData the input data for this use case
     */

    void execute(ViewExpensesInputData viewExpensesInputData);

    /**
     * Executes the switch to homepage use case.
     */

    void switchToHome();

}

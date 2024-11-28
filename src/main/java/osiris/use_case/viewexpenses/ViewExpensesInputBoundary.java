package osiris.use_case.viewexpenses;

import java.io.IOException;

public interface ViewExpensesInputBoundary {

    /**
     * Execute the View Expenses Use Case.
     * @param viewExpensesInputData the input data for this use case
     */

    void execute(ViewExpensesInputData viewExpensesInputData) throws IOException;

    /**
     * Executes the switch to homepage use case.
     */

    void switchToHome();

}

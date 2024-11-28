package osiris.use_case.grabtransactions;

import java.io.IOException;

public interface GrabTransactionsInputBoundary {
    /**
     * Execute the View Expenses Use Case.
     * @param grabTransactionsInputData the input data for this use case
     */

    void execute(GrabTransactionsInputData grabTransactionsInputData) throws IOException;

    /**
     * Executes the switch to homepage use case.
     */

}

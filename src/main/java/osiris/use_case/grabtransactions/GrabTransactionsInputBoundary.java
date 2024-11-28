package osiris.use_case.grabtransactions;

import com.plaid.client.model.Transaction;

import java.io.IOException;
import java.util.List;

public interface GrabTransactionsInputBoundary {
    /**
     * Execute the View Expenses Use Case.
     * @param grabTransactionsInputData the input data for this use case
     */

// In GrabTransactionsInputBoundary
    List<Transaction> execute(GrabTransactionsInputData grabTransactionsInputData) throws IOException;

    /**
     * Executes the switch to homepage use case.
     */

}

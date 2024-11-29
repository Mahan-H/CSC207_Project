package osiris.use_case.grabtransactions;

import com.plaid.client.model.Transaction;
import osiris.utility.exceptions.PlaidUseCaseException;

import java.io.IOException;
import java.util.List;

public interface GrabTransactionsInputBoundary {
    /**
     * Execute the View Expenses Use Case.
     * @param grabTransactionsInputData the input data for this use case
     */

    GrabTransactionOutputData fetchTransactions(GrabTransactionsInputData grabTransactionsInputData) throws IOException, PlaidUseCaseException;
}

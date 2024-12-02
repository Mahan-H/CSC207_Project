package osiris.use_case.grabtransactions;

import osiris.utility.exceptions.PlaidUseCaseException;

import java.io.IOException;

/**
 * The GrabTransactions Input Boundary.
 */
public interface GrabTransactionsInputBoundary {
    /**
     * Execute the View Expenses Use Case.
     * @param grabTransactionsInputData the input data for this use case
     * @return GrabTransactionOutputData containing the List of Transactions
     * @throws IOException when performing input/output operation
     */

    GrabTransactionOutputData fetchTransactions(GrabTransactionsInputData grabTransactionsInputData)
            throws IOException, PlaidUseCaseException;
}

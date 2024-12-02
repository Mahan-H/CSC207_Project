package osiris.use_case.grabtransactions;

import java.io.IOException;

import osiris.utility.exceptions.PlaidUseCaseException;

/**
 * The GrabTransactions Input Boundary.
 */
public interface GrabTransactionsInputBoundary {
    /**
     * Execute the View Expenses Use Case.
     * @param grabTransactionsInputData the input data for this use case
     * @return GrabTransactionOutputData containing the List of Transactions
     * @throws IOException when performing input/output operation
     * @throws PlaidUseCaseException when performing Plaid call
     */

    GrabTransactionOutputData fetchTransactions(GrabTransactionsInputData grabTransactionsInputData)
            throws IOException, PlaidUseCaseException;
}

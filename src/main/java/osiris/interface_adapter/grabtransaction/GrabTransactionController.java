package osiris.interface_adapter.grabtransaction;

import java.io.IOException;

import osiris.use_case.grabtransactions.GrabTransactionOutputData;
import osiris.use_case.grabtransactions.GrabTransactionsInputBoundary;
import osiris.use_case.grabtransactions.GrabTransactionsInputData;
import osiris.utility.exceptions.PlaidUseCaseException;

/**
 * The controller for the GrabTransaction Use Case.
 */
public class GrabTransactionController {
    private final GrabTransactionsInputBoundary grabTransactions;

    public GrabTransactionController(GrabTransactionsInputBoundary grabTransactions) {
        this.grabTransactions = grabTransactions;
    }

    /**
     * Endpoint to create a Plaid Link Token.
     *
     * @param username The request body containing necessary parameters.
     * @return GrabTransactionOutputData containing the List of Transactions
     * @throws PlaidUseCaseException when Plaid fails
     * @throws IOException when performing input/output operation
     */
    public GrabTransactionOutputData createTransactions(String username) throws PlaidUseCaseException, IOException {
        final GrabTransactionsInputData inputData = new GrabTransactionsInputData(username);
        return grabTransactions.fetchTransactions(inputData);
    }

}

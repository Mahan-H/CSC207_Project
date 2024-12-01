package osiris.use_case.grabtransactions;

/**
 * Output Boundary for GrabTransactions UseCase.
 */
public interface GrabTransactionsOutputBoundary {
    /**
     * Presents the transaction data output to the user/system.
     *
     * @param outputData The processed output data containing transaction information.
     */
    void presentTransactions(GrabTransactionOutputData outputData);
}

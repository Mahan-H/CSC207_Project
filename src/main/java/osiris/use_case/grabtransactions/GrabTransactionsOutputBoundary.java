package osiris.use_case.grabtransactions;

import com.plaid.client.model.Transaction;
import java.util.List;

public interface GrabTransactionsOutputBoundary {
    void handleTransactions(List<Transaction> transactions);
}

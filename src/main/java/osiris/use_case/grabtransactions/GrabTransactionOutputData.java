package osiris.use_case.grabtransactions;

import java.util.List;

import com.plaid.client.model.Transaction;
import lombok.Getter;
import lombok.Setter;

/**
 * The GrabTransactions Output Data.
 */

@Setter
@Getter
public class GrabTransactionOutputData {

    private List<Transaction> transactions;

    public GrabTransactionOutputData(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

}

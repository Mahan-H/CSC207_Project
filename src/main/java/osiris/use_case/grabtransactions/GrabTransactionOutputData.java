package osiris.use_case.grabtransactions;

import lombok.Getter;
import lombok.Setter;

/**
 * The GrabTransactions Output Data.
 */

@Setter
@Getter
public class GrabTransactionOutputData {

    private String transactions;

    public GrabTransactionOutputData(String transactions) {
        this.transactions = transactions;
    }

    public String getTransactions() {
        return transactions;
    }

}

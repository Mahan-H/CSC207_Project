package osiris.use_case.grabtransactions;

import com.plaid.client.model.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class GrabTransactionOutputData {

    private List<Transaction> transactions;


    public GrabTransactionOutputData(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Transaction> getTransactions(){
        return transactions;
    }

}

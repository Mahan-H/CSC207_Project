package osiris.use_case.viewexpenses;

import java.util.List;

import com.plaid.client.model.Transaction;
import lombok.Getter;

/**
 * The InputData for the ViewExpenses.
 */
@Getter
public class ViewExpensesInputData {
    private final List<Transaction> transactionList;

    public ViewExpensesInputData(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

}

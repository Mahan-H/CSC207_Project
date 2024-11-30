package osiris.use_case.viewexpenses;

import com.plaid.client.model.Transaction;

import java.util.List;

public class ViewExpensesInputData {
    List<Transaction> transactionList;


    public ViewExpensesInputData(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }


}

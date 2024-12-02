package osiris.use_case.viewexpenses;

import lombok.Getter;

/**
 * The InputData for the ViewExpenses.
 */
@Getter
public class ViewExpensesInputData {
    private final String transactionList;

    public ViewExpensesInputData(String transactionList) {
        this.transactionList = transactionList;
    }

}

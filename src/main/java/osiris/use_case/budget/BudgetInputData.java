package osiris.use_case.budget;

import lombok.Getter;

/**
 * The InputData for the budget.
 */
@Getter
public class BudgetInputData {
    private final String transactionList;

    public BudgetInputData(String transactionList) {
        this.transactionList = transactionList;
    }

}

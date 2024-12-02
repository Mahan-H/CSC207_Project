package osiris.use_case.budgetamount;

/**
 * The Input Data for the Budget Amount Use Case.
 */
public class BudgetAmountInputData {
    private final String amount;

    public BudgetAmountInputData(String amount) {
        this.amount = amount;
    }

    public String getAmount() {
        return this.amount;
    }

}

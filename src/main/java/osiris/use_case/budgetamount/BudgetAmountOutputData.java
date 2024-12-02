package osiris.use_case.budgetamount;

/**
 * Output Data for the Budget Amount Use Case.
 */
public class BudgetAmountOutputData {

    private final int amount;
    private final boolean useCaseFailed;

    public BudgetAmountOutputData(int amount, boolean useCaseFailed) {
        this.amount = amount;
        this.useCaseFailed = useCaseFailed;
    }

}

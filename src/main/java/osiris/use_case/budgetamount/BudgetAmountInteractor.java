package osiris.use_case.budgetamount;



/**
 * The Budget Amount Interactor.
 */
public class BudgetAmountInteractor implements BudgetAmountInputBoundary {
    private final BudgetAmountOutputBoundary budgetAmountOutputBoundary;

    public BudgetAmountInteractor(BudgetAmountOutputBoundary budgetAmountOutputBoundary) {
        this.budgetAmountOutputBoundary = budgetAmountOutputBoundary;
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    public void execute(BudgetAmountInputData budgetAmountInputData) {
        final String amount = budgetAmountInputData.getAmount();
        if (!isInteger(amount)) {
            final int intAmount = Integer.parseInt(amount);
            BudgetAmountOutputData budgetAmountOutputData = new BudgetAmountOutputData(intAmount, false);
            budgetAmountOutputBoundary.prepareSuccessView(budgetAmountOutputData);
        }
    }

    public void switchToBudgetView() {
        budgetAmountOutputBoundary.switchToBudgetView();
    }

    public void switchToDashboardView() {
        budgetAmountOutputBoundary.switchToDashboardView();
    }
}


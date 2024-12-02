package osiris.interface_adapter.budgetamount;

import osiris.use_case.budgetamount.BudgetAmountInputBoundary;
import osiris.use_case.budgetamount.BudgetAmountInputData;

public class BudgetAmountController {

    private final BudgetAmountInputBoundary budgetAmountInputBoundary;

    public BudgetAmountController(BudgetAmountInputBoundary budgetAmountInputBoundary) {
        this.budgetAmountInputBoundary = budgetAmountInputBoundary;
    }

    /**
     * Executes the BudgetAmount Use Case.
     *  @param amount the amount of the budget
     */
    public void execute(String amount) {
        //int intAmount = Integer.parseInt(amount);
        final BudgetAmountInputData budgetAmountInputData = new BudgetAmountInputData(amount);

        budgetAmountInputBoundary.execute(budgetAmountInputData);
    }

    /**
     * Executes the "switch to BudgetView" Use Case.
     */
    public void switchToBudgetView() {
        budgetAmountInputBoundary.switchToBudgetView();
    }

    /**
     * Executes the "switch to DashboardView" Use Case.
     */
    public void switchToDashboardView() {
        budgetAmountInputBoundary.switchToDashboardView();
    }
}

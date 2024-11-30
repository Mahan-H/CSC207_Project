package osiris.interface_adapter.viewexpenses;

import com.plaid.client.model.Transaction;
import osiris.interface_adapter.grabtransaction.GrabTransactionController;
import osiris.use_case.viewexpenses.ViewExpensesInputBoundary;
import osiris.use_case.viewexpenses.ViewExpensesInputData;
import osiris.utility.jfreechart.PieChartUtility;

import java.util.List;

public class ViewExpensesController {
    private final ViewExpensesInputBoundary interactor;

    public ViewExpensesController(ViewExpensesInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(List<Transaction> transactionList) {
        ViewExpensesInputData inputData = new ViewExpensesInputData(transactionList);
        interactor.handle(inputData);
    }
}

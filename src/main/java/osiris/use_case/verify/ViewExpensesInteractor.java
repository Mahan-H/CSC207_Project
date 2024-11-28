package osiris.use_case.verify;


import osiris.use_case.grabtransactions.GrabTransactions;
import osiris.use_case.viewexpenses.ViewExpensesInputBoundary;
import osiris.use_case.viewexpenses.ViewExpensesInputData;

public class ViewExpensesInteractor implements ViewExpensesInputBoundary {
    private final GrabTransactions grabTransactions;

    public ViewExpensesInteractor(GrabTransactions grabTransactions) {
        this.grabTransactions = grabTransactions;
    }


    @Override
    public void execute(ViewExpensesInputData viewExpensesInputData) {

    }

    @Override
    public void switchToHome() {
        // implementation for switching to home
    }
}

package osiris.use_case.viewexpenses;

import java.util.List;
import java.util.Set;

import com.plaid.client.model.Transaction;

/**
 * The GrabTransaction Interactor.
 */
public class ViewExpensesInteractor implements ViewExpensesInputBoundary {
    private final ViewExpensesOutputBoundary presenter;
    private final Set<String> esscategories = Set.of("Groceries", "Rent", "Utilities", "Healthcare");

    public ViewExpensesInteractor(ViewExpensesOutputBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public void handle(ViewExpensesInputData inputData) {
        // Fetch transactions from Plaid API
        final List<Transaction> transactions = inputData.getTransactionList();

        // Initialize totals
        double essentialTotal = 0.0;
        double nonEssentialTotal = 0.0;

        // Categorize transactions
        for (Transaction transaction : transactions) {
            final List<String> categories = transaction.getCategory();
            final double amount = transaction.getAmount();

            if (categories != null && isEssential(categories)) {
                essentialTotal += amount;
            }
            else {
                nonEssentialTotal += amount;
            }
        }

        final ViewExpensesOutputData outputData = new ViewExpensesOutputData(essentialTotal, nonEssentialTotal);
        presenter.prepareChart(outputData);
    }

    @Override
    public void switchToHomeView() {
        presenter.switchToHomeView();

    }

    private boolean isEssential(List<String> categories) {
        // Check if any category in the list matches the essential category
        boolean count = false;
        for (String category : categories) {
            if (esscategories.contains(category)) {
                count = true;
                break;
            }
        }
        return count;
    }
}

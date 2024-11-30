package osiris.use_case.viewexpenses;

import com.plaid.client.model.Transaction;
import java.util.List;
import java.util.Set;

public class ViewExpensesInteractor implements ViewExpensesInputBoundary {
    private final ViewExpensesOutputBoundary presenter;
    private static final Set<String> ESSENTIAL_CATEGORIES = Set.of("Groceries", "Rent", "Utilities", "Healthcare");

    public ViewExpensesInteractor(ViewExpensesOutputBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public void handle(ViewExpensesInputData inputData) {
        // Fetch transactions from Plaid API
        List<Transaction> transactions = inputData.getTransactionList();

        // Initialize totals
        double essentialTotal = 0.0;
        double nonEssentialTotal = 0.0;

        // Categorize transactions
        for (Transaction transaction : transactions) {
            List<String> categories = transaction.getCategory();
            double amount = transaction.getAmount();

            if (categories != null && isEssential(categories)) {
                essentialTotal += amount;
            } else {
                nonEssentialTotal += amount;
            }
        }

        // Create output data
        ViewExpensesOutputData outputData = new ViewExpensesOutputData(essentialTotal, nonEssentialTotal);
        // Pass data to presenter
        presenter.PrepareChart(outputData);
    }

    @Override
    public void switchToHomeView() {
        presenter.switchToHomeView();

    }

    private boolean isEssential(List<String> categories) {
        // Check if any category in the list matches the essential category
        for (String category : categories) {
            if (ESSENTIAL_CATEGORIES.contains(category)) {
                return true;
            }
        }
        return false;
    }
}

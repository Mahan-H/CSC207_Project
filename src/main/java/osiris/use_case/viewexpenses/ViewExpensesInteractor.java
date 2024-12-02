package osiris.use_case.viewexpenses;

import java.util.HashSet;
import java.util.Set;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
// import osiris.utility.jfreechart.PieChartUtility;

/**
 * The GrabTransaction Interactor.
 */
public class ViewExpensesInteractor implements ViewExpensesInputBoundary {
    private final ViewExpensesOutputBoundary presenter;

    public ViewExpensesInteractor(ViewExpensesOutputBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute(ViewExpensesInputData inputData) {
        // Fetch transactions from Plaid API
        final String transactionsJson = inputData.getTransactionList();

        final Set<String> essentialCategories = new HashSet<>();
        essentialCategories.add("Groceries");
        essentialCategories.add("Rent");
        essentialCategories.add("Utilities");
        essentialCategories.add("Healthcare");
        // Initialize totals
        double essentialTotal = 0.0;
        double nonEssentialTotal = 0.0;

        // Categorize transactions
        final JsonObject jsonResponse = JsonParser.parseString(transactionsJson).getAsJsonObject();
        final JsonArray transactions = jsonResponse.getAsJsonArray("transactions");

        for (JsonElement transactionElement : transactions) {
            final JsonObject transaction = transactionElement.getAsJsonObject();
            final double amount = transaction.get("amount").getAsDouble();
            final JsonArray categories = transaction.getAsJsonArray("category");

            boolean isEssential = false;
            for (JsonElement categoryElement : categories) {
                final String category = categoryElement.getAsString();
                if (essentialCategories.contains(category)) {
                    isEssential = true;
                    break;
                }
            }
            if (isEssential) {
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

}

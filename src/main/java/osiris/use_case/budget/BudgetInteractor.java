package osiris.use_case.budget;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The Budget Interactor.
 */
public class BudgetInteractor implements BudgetInputBoundary {
    private final BudgetOutputBoundary presenter;

    public BudgetInteractor(BudgetOutputBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute(BudgetInputData inputData) {
        // Fetch transactions from Plaid API
        final String transactionsJson = inputData.getTransactionList();

        double jan = 0.0;
        double feb = 0.0;
        double mar = 0.0;
        double apr = 0.0;
        double may = 0.0;
        double jun = 0.0;
        double jul = 0.0;
        double aug = 0.0;
        double sep = 0.0;
        double oct = 0.0;
        double nov = 0.0;
        double dec = 0.0;
        ArrayList<Double> monthlyTotals = new ArrayList<>(Arrays.asList(jan, feb, mar, apr, may, jun, jul, aug,
                sep, oct, nov, dec));

        // Categorize transactions into months
        final JsonObject jsonResponse = JsonParser.parseString(transactionsJson).getAsJsonObject();
        final JsonArray transactions = jsonResponse.getAsJsonArray("transactions");

        for (JsonElement transactionElement : transactions) {
            final JsonObject transaction = transactionElement.getAsJsonObject();
            final double amount = transaction.get("amount").getAsDouble();
            final String strDate = transaction.get("date").getAsString();
            final int monthIndex = Integer.parseInt(strDate.substring(5, 7));

            double newAmount = monthlyTotals.get(monthIndex) + amount;
            monthlyTotals.set(monthIndex, newAmount);
        }

        final BudgetOutputData outputData = new BudgetOutputData(monthlyTotals);

    }

    @Override
    public void switchToDashboardView() {
        presenter.switchToDashboardView();
    }
}

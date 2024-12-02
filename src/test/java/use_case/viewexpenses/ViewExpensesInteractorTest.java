package use_case.viewexpenses;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import osiris.use_case.viewexpenses.ViewExpensesInputData;
import osiris.use_case.viewexpenses.ViewExpensesInteractor;
import osiris.use_case.viewexpenses.ViewExpensesOutputBoundary;

import static org.mockito.Mockito.*;

class ViewExpensesInteractorTest {

    private ViewExpensesOutputBoundary presenter;
    private ViewExpensesInteractor interactor;

    @BeforeEach
    void setUp() {
        presenter = mock(ViewExpensesOutputBoundary.class);
        interactor = new ViewExpensesInteractor(presenter);
    }

    @Test
    void testExecuteWithEssentialAndNonEssentialTransactions() {
        // Arrange
        String transactionsJson = createMockTransactionsJson();
        ViewExpensesInputData inputData = new ViewExpensesInputData(transactionsJson);

        // Act
        interactor.execute(inputData);

        // Assert
        verify(presenter, times(1)).prepareChart(argThat(outputData ->
                outputData.getEssentialTotal() == 80.0 && outputData.getNonEssentialTotal() == 40.0));
    }

    @Test
    void testExecuteWithNoTransactions() {
        // Arrange
        String transactionsJson = "{\"transactions\":[]}";
        ViewExpensesInputData inputData = new ViewExpensesInputData(transactionsJson);

        // Act
        interactor.execute(inputData);

        // Assert
        verify(presenter, times(1)).prepareChart(argThat(outputData ->
                outputData.getEssentialTotal() == 0.0 && outputData.getNonEssentialTotal() == 0.0));
    }

    @Test
    void testSwitchToHomeView() {
        // Act
        interactor.switchToHomeView();

        // Assert
        verify(presenter, times(1)).switchToHomeView();
    }

    /**
     * Helper method to create mock JSON transactions.
     */
    private String createMockTransactionsJson() {
        JsonArray transactions = new JsonArray();

        // Essential transaction
        JsonObject essentialTransaction = new JsonObject();
        essentialTransaction.addProperty("amount", 50.0);
        JsonArray essentialCategory = new JsonArray();
        essentialCategory.add("Groceries");
        essentialTransaction.add("category", essentialCategory);
        transactions.add(essentialTransaction);

        // Another essential transaction
        JsonObject anotherEssentialTransaction = new JsonObject();
        anotherEssentialTransaction.addProperty("amount", 30.0);
        JsonArray anotherEssentialCategory = new JsonArray();
        anotherEssentialCategory.add("Rent");
        anotherEssentialTransaction.add("category", anotherEssentialCategory);
        transactions.add(anotherEssentialTransaction);

        // Non-essential transaction
        JsonObject nonEssentialTransaction = new JsonObject();
        nonEssentialTransaction.addProperty("amount", 40.0);
        JsonArray nonEssentialCategory = new JsonArray();
        nonEssentialCategory.add("Entertainment");
        nonEssentialTransaction.add("category", nonEssentialCategory);
        transactions.add(nonEssentialTransaction);

        // Wrap in JSON response
        JsonObject response = new JsonObject();
        response.add("transactions", transactions);

        return response.toString();
    }
}

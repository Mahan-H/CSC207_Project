package use_case.viewexpenses;

import com.plaid.client.model.Transaction;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import osiris.interface_adapter.viewexpenses.ViewExpensesController;
import osiris.use_case.viewexpenses.ViewExpensesInputBoundary;
import osiris.use_case.viewexpenses.ViewExpensesInputData;

import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ViewExpensesControllerTest {

    @Test
    void testExecute() {
        // Mock the input boundary (interactor)
        ViewExpensesInputBoundary mockInteractor = mock(ViewExpensesInputBoundary.class);

        // Create the controller
        ViewExpensesController controller = new ViewExpensesController(mockInteractor);

        // Create a mock transaction list
        Transaction mockTransaction = new Transaction();
        mockTransaction.setAmount(100.0);
        mockTransaction.setCategory(Collections.singletonList("Groceries"));
        mockTransaction.setName("Test Transaction");

        // Call execute with the mock transaction
        controller.execute(Collections.singletonList(mockTransaction));

        // Verify that the interactor's handle method was called with the correct data
        verify(mockInteractor).handle(Mockito.any(ViewExpensesInputData.class));

        verify(mockInteractor).handle(Mockito.argThat(inputData ->
                inputData.getTransactionList().get(0).getAmount() == 100.0 &&
                        inputData.getTransactionList().get(0).getCategory().contains("Groceries")
        ));

    }
}

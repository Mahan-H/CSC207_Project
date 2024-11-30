package use_case.viewexpenses;

import com.plaid.client.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import osiris.use_case.viewexpenses.ViewExpensesInputData;
import osiris.use_case.viewexpenses.ViewExpensesInteractor;
import osiris.use_case.viewexpenses.ViewExpensesOutputBoundary;
import osiris.use_case.viewexpenses.ViewExpensesOutputData;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ViewExpensesInteractorTest {

    private ViewExpensesOutputBoundary presenter;
    private ViewExpensesInteractor interactor;

    @BeforeEach
    void setUp() {
        presenter = mock(ViewExpensesOutputBoundary.class);
        interactor = new ViewExpensesInteractor(presenter);
    }

    @Test
    void testHandle() {
        // Create mock transactions
        List<Transaction> transactions = new ArrayList<>();

        Transaction essential1 = new Transaction();
        essential1.setAmount(500.0);
        essential1.setCategory(List.of("Groceries"));

        Transaction essential2 = new Transaction();
        essential2.setAmount(800.0);
        essential2.setCategory(List.of("Rent"));

        Transaction nonEssential1 = new Transaction();
        nonEssential1.setAmount(200.0);
        nonEssential1.setCategory(List.of("Entertainment"));

        Transaction nonEssential2 = new Transaction();
        nonEssential2.setAmount(100.0);
        nonEssential2.setCategory(List.of("Dining Out"));

        transactions.add(essential1);
        transactions.add(essential2);
        transactions.add(nonEssential1);
        transactions.add(nonEssential2);

        // Pass the data to the interactor
        ViewExpensesInputData inputData = new ViewExpensesInputData(transactions);
        interactor.handle(inputData);

        // Capture the output data
        ArgumentCaptor<ViewExpensesOutputData> outputCaptor = ArgumentCaptor.forClass(ViewExpensesOutputData.class);
        verify(presenter).prepareChart(outputCaptor.capture());
        ViewExpensesOutputData outputData = outputCaptor.getValue();

        // Validate the totals
        assertEquals(1300.0, outputData.getEssentialTotal(), "Essential total does not match");
        assertEquals(300.0, outputData.getNonEssentialTotal(), "Non-essential total does not match");
    }
}

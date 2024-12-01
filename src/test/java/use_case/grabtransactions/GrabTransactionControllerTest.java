package use_case.grabtransactions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import osiris.interface_adapter.grabtransaction.GrabTransactionController;
import osiris.use_case.grabtransactions.GrabTransactionOutputData;
import osiris.use_case.grabtransactions.GrabTransactionsInputBoundary;
import osiris.use_case.grabtransactions.GrabTransactionsInputData;
import osiris.utility.exceptions.PlaidUseCaseException;

import java.io.IOException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

public class GrabTransactionControllerTest {

    private GrabTransactionController grabTransactionController;
    private GrabTransactionsInputBoundary mockGrabTransactions;

    @BeforeEach
    public void setUp() {
        // Create a mock for the GrabTransactionsInputBoundary
        mockGrabTransactions = Mockito.mock(GrabTransactionsInputBoundary.class);
        grabTransactionController = new GrabTransactionController(mockGrabTransactions);
    }

    @Test
    public void testCreateTransactions() throws PlaidUseCaseException, IOException {
        // Prepare mock data
        String username = "test_user";
        GrabTransactionOutputData mockOutputData = new GrabTransactionOutputData(Collections.emptyList());

        // Configure the mock to return the prepared output data
        Mockito.when(mockGrabTransactions.fetchTransactions(any(GrabTransactionsInputData.class)))
                .thenReturn(mockOutputData);

        // Call the method under test
        GrabTransactionOutputData result = grabTransactionController.createTransactions(username);

        // Verify the result and mock interactions
        assertEquals(mockOutputData, result);
        Mockito.verify(mockGrabTransactions).fetchTransactions(any(GrabTransactionsInputData.class));
    }
}

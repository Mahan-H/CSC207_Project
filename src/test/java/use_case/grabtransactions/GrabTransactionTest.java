package use_case.grabtransactions;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.plaid.client.model.Transaction;
import com.plaid.client.request.PlaidApi;
import osiris.data_access.PlaidDataAccessObject;
import osiris.entity.User;
import osiris.use_case.grabtransactions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import osiris.utility.exceptions.PlaidException;
import osiris.utility.exceptions.PlaidUseCaseException;

import java.io.IOException;
import java.util.List;

class GrabTransactionsTest {

    @Mock
    private PlaidDataAccessObject plaidDao;

    @Mock
    private GrabTransactionUserDataAccessInterface userDataAccessObject;

    @InjectMocks
    private GrabTransactionsInteractor grabTransactions;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFetchTransactionsSuccess() throws Exception {
        // Standard test case to verify successful transaction fetch
        String username = "testUser";
        User mockUser = mock(User.class);
        when(mockUser.getAccessCode()).thenReturn("test-access-token");
        when(userDataAccessObject.get(username)).thenReturn(mockUser);

        Transaction mockTransaction = mock(Transaction.class);
        when(mockTransaction.getName()).thenReturn("Amazon");
        when(plaidDao.fetchTransactions(anyString())).thenReturn(List.of(mockTransaction));

        GrabTransactionsInputData inputData = new GrabTransactionsInputData(username);

        GrabTransactionOutputData outputData = grabTransactions.fetchTransactions(inputData);

        assertNotNull(outputData.getTransactions());
        assertEquals(1, outputData.getTransactions().size());
        assertEquals("Amazon", outputData.getTransactions().get(0).getName());
    }

    @Test
    void testFetchTransactionsThrowsPlaidException() throws Exception {
        // Simulate a PlaidException being thrown
        String username = "testUser";
        User mockUser = mock(User.class);
        when(mockUser.getAccessCode()).thenReturn("test-access-token");
        when(userDataAccessObject.get(username)).thenReturn(mockUser);

        when(plaidDao.fetchTransactions(anyString()))
                .thenThrow(new PlaidException("Plaid API error", null));

        GrabTransactionsInputData inputData = new GrabTransactionsInputData(username);

        PlaidUseCaseException exception = assertThrows(PlaidUseCaseException.class, () -> {
            grabTransactions.fetchTransactions(inputData);
        });

        assertTrue(exception.getMessage().contains("Failed to create transactions"));
    }

    @Test
    void testFetchTransactionsThrowsIOException() throws Exception {
        // Simulate an IOException being thrown
        String username = "testUser";
        User mockUser = mock(User.class);
        when(mockUser.getAccessCode()).thenReturn("test-access-token");
        when(userDataAccessObject.get(username)).thenReturn(mockUser);

        when(plaidDao.fetchTransactions(anyString())).thenThrow(new IOException("IO Error"));

        GrabTransactionsInputData inputData = new GrabTransactionsInputData(username);

        PlaidUseCaseException exception = assertThrows(PlaidUseCaseException.class, () -> {
            grabTransactions.fetchTransactions(inputData);
        });

        assertTrue(exception.getMessage().contains("IO Error while creating transactions"));
    }
}

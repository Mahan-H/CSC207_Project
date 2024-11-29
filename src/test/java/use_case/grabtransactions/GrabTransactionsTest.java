package use_case.grabtransactions;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.plaid.client.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import osiris.data_access.PlaidDataAccessObject;
import osiris.entity.User;
import osiris.use_case.grabtransactions.*;

import java.time.LocalDate;
import java.util.List;

class GrabTransactionsTest {

    @Mock
    private PlaidDataAccessObject plaidDao;

    @Mock
    private GrabTransactionUserDataAccessInterface userDataAccessObject;

    @InjectMocks
    private GrabTransactions grabTransactions;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFetchTransactionsSuccess() throws Exception {
        // Given
        String username = "testUser";
        User mockUser = mock(User.class);
        when(mockUser.getAccessCode()).thenReturn("access-sandbox-xxx");
        when(userDataAccessObject.get(username)).thenReturn(mockUser);

        // Mocking the Transaction object
        Transaction mockTransaction = mock(Transaction.class);
        when(mockTransaction.getName()).thenReturn("Amazon");
        when(mockTransaction.getAmount()).thenReturn(-19.99);
        when(mockTransaction.getDate()).thenReturn(LocalDate.now());

        List<Transaction> expectedTransactions = List.of(mockTransaction);
        when(plaidDao.fetchTransactions(anyString())).thenReturn(expectedTransactions);

        GrabTransactionsInputData inputData = new GrabTransactionsInputData(username);

        // When
        GrabTransactionOutputData outputData = grabTransactions.fetchTransactions(inputData);

        // Then
        List<Transaction> transactions = outputData.getTransactions();
        assertNotNull(transactions);
        assertFalse(transactions.isEmpty());
        assertEquals("Amazon", transactions.get(0).getName());

        // Print transaction details
        transactions.forEach(transaction -> {
            System.out.println("Transaction Name: " + transaction.getName());
            System.out.println("Amount: " + transaction.getAmount());
            System.out.println("Date: " + transaction.getDate());
            // Add more details as required
        });

        // Verify interactions
        verify(plaidDao).fetchTransactions(mockUser.getAccessCode());
    }
}

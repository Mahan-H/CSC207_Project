package use_case.grabtransactions;

import osiris.data_access.PlaidDataAccessObject;
import osiris.data_access.DBUserDataAccessObject;
import osiris.entity.User;
import osiris.use_case.grabtransactions.GrabTransactionsInputData;
import osiris.use_case.grabtransactions.GrabTransactionsInteractor;
import osiris.use_case.grabtransactions.GrabTransactionOutputData;

import osiris.utility.exceptions.PlaidUseCaseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GrabTransactionsInteractorTest {

    private GrabTransactionsInteractor interactor;
    private DBUserDataAccessObject userDataAccessObject;
    private PlaidDataAccessObject plaidDao;

    @BeforeEach
    public void setUp() {
        userDataAccessObject = mock(DBUserDataAccessObject.class);
        plaidDao = mock(PlaidDataAccessObject.class);
        interactor = new GrabTransactionsInteractor(userDataAccessObject, plaidDao);
    }

    @Test
    public void testFetchTransactionsSuccess() throws IOException, PlaidUseCaseException {
        // Arrange
        String email = "validUser@example.com";
        String accessToken = "validAccessToken";
        String mockTransactions = "mockTransactions";

        GrabTransactionsInputData inputData = new GrabTransactionsInputData(email);
        User mockUser = mock(User.class);

        when(mockUser.getAccessCode()).thenReturn(accessToken);
        when(userDataAccessObject.get(email)).thenReturn(mockUser);
        when(plaidDao.fetchTransactions(accessToken)).thenReturn(mockTransactions);

        // Act
        GrabTransactionOutputData outputData = interactor.fetchTransactions(inputData);

        // Assert
        assertNotNull(outputData);
        assertEquals(mockTransactions, outputData.getTransactions());
        verify(userDataAccessObject, times(1)).get(email);
        verify(plaidDao, times(1)).fetchTransactions(accessToken);
    }

    @Test
    public void testFetchTransactionsUserNotFound() throws IOException {
        // Arrange
        String email = "unknownUser@example.com";
        GrabTransactionsInputData inputData = new GrabTransactionsInputData(email);

        when(userDataAccessObject.get(email)).thenReturn(null);

        // Act & Assert
        Exception exception = assertThrows(PlaidUseCaseException.class, () -> {
            interactor.fetchTransactions(inputData);
        });

        assertEquals("User not found: " + email, exception.getMessage());
        verify(userDataAccessObject, times(1)).get(email);
        verify(plaidDao, never()).fetchTransactions(anyString());
    }

    @Test
    public void testFetchTransactionsPlaidError() throws IOException {
        // Arrange
        String email = "validUser@example.com";
        String accessToken = "validAccessToken";

        GrabTransactionsInputData inputData = new GrabTransactionsInputData(email);
        User mockUser = mock(User.class);

        when(mockUser.getAccessCode()).thenReturn(accessToken);
        when(userDataAccessObject.get(email)).thenReturn(mockUser);
        when(plaidDao.fetchTransactions(accessToken)).thenThrow(new IOException("Plaid API error"));

        // Act & Assert
        Exception exception = assertThrows(IOException.class, () -> {
            interactor.fetchTransactions(inputData);
        });

        assertEquals("IO Error while creating transactions", exception.getMessage());
        verify(userDataAccessObject, times(1)).get(email);
        verify(plaidDao, times(1)).fetchTransactions(accessToken);
    }
}

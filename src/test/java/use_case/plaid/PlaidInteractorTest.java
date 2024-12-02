package use_case.plaid;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import osiris.data_access.PlaidDataAccessObject;
import osiris.data_access.PlaidDataAccessObject.ExchangeTokenResponse;
import osiris.data_access.PlaidDataAccessObject.LinkTokenResponse;
import osiris.entity.CommonUserFactory;
import osiris.entity.User;
import osiris.entity.UserFactory;
import osiris.use_case.plaid.*;
import osiris.utility.exceptions.PlaidException;
import osiris.utility.exceptions.PlaidUseCaseException;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlaidInteractorTest {

    private static PlaidDataAccessObject mockPlaidDao;
    private static PlaidInteractor interactor;
    private static PlaidDataBaseUserAccessObjectInterface mockUserRepository;

    @BeforeAll
    static void setUp() throws SQLException {
        mockPlaidDao = mock(PlaidDataAccessObject.class);
        UserFactory mockFactory = mock(UserFactory.class);
        mockUserRepository = mock(PlaidDataBaseUserAccessObjectInterface.class);

        // Mock user creation
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("test_name4", "password", "test-access-token");
        when(mockFactory.create(any(), any(), any())).thenReturn(user);

        // Mock user save
        doNothing().when(mockUserRepository).save(any(User.class));

        interactor = new PlaidInteractor(mockPlaidDao, mockUserRepository, mockFactory);
    }

    @BeforeEach
    void resetMocks() {
        reset(mockPlaidDao);
    }


    @Test
    void testCreateLinkTokenSuccess() throws Exception {
        CreateLinkTokenInputData inputData = new CreateLinkTokenInputData(
                "TestApp",
                new String[]{"US"},
                "en",
                "test-client-id",
                new String[]{"auth", "transactions"}
        );

        LinkTokenResponse mockResponse = new LinkTokenResponse("test-link-token", "test-request-id");
        when(mockPlaidDao.createLinkToken(any(), any(), any(), any(), any())).thenReturn(mockResponse);

        CreateLinkTokenOutputData result = interactor.createLinkToken(inputData);

        assertEquals("test-link-token", result.getLinkToken());
        assertEquals("test-request-id", result.getRequestId());
        verify(mockPlaidDao, times(1)).createLinkToken(
                eq("TestApp"), eq(new String[]{"US"}), eq("en"),
                eq("test-client-id"), eq(new String[]{"auth", "transactions"})
        );
    }

    @Test
    void testCreateLinkTokenPlaidException() throws IOException, PlaidException {
        CreateLinkTokenInputData inputData = new CreateLinkTokenInputData(
                "TestApp",
                new String[]{"US"},
                "en",
                "test-client-id",
                new String[]{"auth", "transactions"}
        );

        when(mockPlaidDao.createLinkToken(any(), any(), any(), any(), any()))
                .thenThrow(new PlaidException("Plaid API Error"));

        PlaidUseCaseException exception = assertThrows(PlaidUseCaseException.class, () -> {
            interactor.createLinkToken(inputData);
        });

        assertTrue(exception.getMessage().contains("Failed to create Link Token"));
        verify(mockPlaidDao, times(1)).createLinkToken(
                any(), any(), any(), any(), any()
        );
    }

    @Test
    void testCreateLinkTokenIOException() throws IOException, PlaidException {
        CreateLinkTokenInputData inputData = new CreateLinkTokenInputData(
                "TestApp",
                new String[]{"US"},
                "en",
                "test-client-id",
                new String[]{"auth", "transactions"}
        );

        when(mockPlaidDao.createLinkToken(any(), any(), any(), any(), any()))
                .thenThrow(new IOException("IO Error"));

        PlaidUseCaseException exception = assertThrows(PlaidUseCaseException.class, () -> {
            interactor.createLinkToken(inputData);
        });

        assertTrue(exception.getMessage().contains("IO Error while creating Link Token"));
    }

    @Test
    void testExchangePublicTokenSuccess() throws IOException, PlaidException, PlaidUseCaseException {
        ExchangePublicTokenInputData inputData = new ExchangePublicTokenInputData(
                "test-public-token",
                "test-client-id"
        );

        ExchangeTokenResponse mockResponse = new ExchangeTokenResponse(
                "test-access-token", "test-item-id", "test-request-id"
        );

        when(mockPlaidDao.exchangePublicToken(any())).thenReturn(mockResponse);

        ExchangePublicTokenOutputData result = interactor.exchangePublicToken(inputData);

        assertEquals("test-access-token", result.getAccessToken());
        assertEquals("test-item-id", result.getItemId());
        assertEquals("test-request-id", result.getRequestId());
        verify(mockPlaidDao, times(1)).exchangePublicToken(eq("test-public-token"));
    }

    @Test
    void testExchangePublicTokenPlaidException() throws IOException, PlaidException {
        ExchangePublicTokenInputData inputData = new ExchangePublicTokenInputData(
                "test-public-token",
                "test-client-id"
        );

        when(mockPlaidDao.exchangePublicToken(any()))
                .thenThrow(new PlaidException("Plaid API Error"));

        PlaidUseCaseException exception = assertThrows(PlaidUseCaseException.class, () -> {
            interactor.exchangePublicToken(inputData);
        });

        assertTrue(exception.getMessage().contains("Failed to exchange Public Token"));
    }

    @Test
    void testExchangePublicTokenIOException() throws IOException, PlaidException {
        ExchangePublicTokenInputData inputData = new ExchangePublicTokenInputData(
                "test-public-token",
                "test-client-id"
        );

        // Mocking IOException
        when(mockPlaidDao.exchangePublicToken(any()))
                .thenThrow(new IOException("IO Error"));

        // Assert exception
        PlaidUseCaseException exception = assertThrows(PlaidUseCaseException.class, () -> {
            interactor.exchangePublicToken(inputData);
        });

        // Assert exception message
        assertTrue(exception.getMessage().contains("IO Error while exchanging Public Token"));

        // Verify mock interaction
        verify(mockPlaidDao, times(1)).exchangePublicToken(eq("test-public-token"));
    }

    @Test
    void testCreateLinkTokenUnhandledException() throws IOException {
        CreateLinkTokenInputData inputData = new CreateLinkTokenInputData(
                "TestApp",
                new String[]{"US"},
                "en",
                "test-client-id",
                new String[]{"auth", "transactions"}
        );

        // Mock an unhandled exception (e.g., NullPointerException)
        when(mockPlaidDao.createLinkToken(any(), any(), any(), any(), any()))
                .thenThrow(new NullPointerException("Unexpected error"));

        // Assert that the RuntimeException is thrown
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            interactor.createLinkToken(inputData);
        });

        // Assert that the cause of the RuntimeException is NullPointerException
        assertTrue(exception.getCause() instanceof NullPointerException);

        // Assert the message of the root cause (NullPointerException)
        assertEquals("Unexpected error", exception.getCause().getMessage());
    }

}

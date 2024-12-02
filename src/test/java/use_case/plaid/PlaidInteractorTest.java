package use_case.plaid;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

    private static UserPlaidDataAccessInterface mockPlaidDao;
    private static PlaidDataBaseUserAccessObjectInterface mockUserRepository;
    private static UserFactory mockUserFactory;
    private static PlaidInteractor interactor;

    @BeforeAll
    static void setUp() {
        mockPlaidDao = mock(UserPlaidDataAccessInterface.class);
        mockUserRepository = mock(PlaidDataBaseUserAccessObjectInterface.class);
        mockUserFactory = mock(UserFactory.class);

        interactor = new PlaidInteractor(mockPlaidDao, mockUserRepository, mockUserFactory);
    }

    @BeforeEach
    void resetMocks() {
        reset(mockPlaidDao, mockUserRepository, mockUserFactory);
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
    void testCreateLinkTokenPlaidException() throws Exception {
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
        verify(mockPlaidDao, times(1)).createLinkToken(any(), any(), any(), any(), any());
    }

    @Test
    void testExchangePublicTokenSuccess() throws Exception {
        ExchangePublicTokenInputData inputData = new ExchangePublicTokenInputData(
                "test-public-token",
                "test-client-id"
        );

        ExchangeTokenResponse mockResponse = new ExchangeTokenResponse(
                "test-access-token", "test-item-id", "test-request-id"
        );
        User mockUser = new User() {
            @Override
            public String getUser() {
                return "";
            }

            @Override
            public String getPassword() {
                return "";
            }

            @Override
            public String getAccessCode() {
                return "";
            }
        };
        User newUser = new User() {
            @Override
            public String getUser() {
                return "";
            }

            @Override
            public String getPassword() {
                return "";
            }

            @Override
            public String getAccessCode() {
                return "";
            }
        };

        when(mockPlaidDao.exchangePublicToken(any())).thenReturn(mockResponse);
        when(mockUserRepository.get(any())).thenReturn(mockUser);
        when(mockUserFactory.create(any(), any(), any())).thenReturn(newUser);

        ExchangePublicTokenOutputData result = interactor.exchangePublicToken(inputData);

        assertEquals("test-access-token", result.getAccessToken());
        assertEquals("test-item-id", result.getItemId());
        assertEquals("test-request-id", result.getRequestId());

        verify(mockUserRepository, times(1)).save(newUser);
    }

    @Test
    void testExchangePublicTokenSQLException() throws IOException, PlaidException {
        // Arrange: Create input data
        ExchangePublicTokenInputData inputData = new ExchangePublicTokenInputData(
                "test-public-token",
                "test-client-id"
        );

        // Mock the response for exchangePublicToken
        ExchangeTokenResponse mockResponse = new ExchangeTokenResponse(
                "test-access-token", "test-item-id", "test-request-id"
        );
        when(mockPlaidDao.exchangePublicToken(any())).thenReturn(mockResponse);

        // Mock RuntimeException wrapping SQLException
        when(mockUserRepository.get(any()))
                .thenThrow(new RuntimeException(new SQLException("Simulated database error")));

        // Act & Assert: Ensure RuntimeException is thrown and contains the SQLException
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            interactor.exchangePublicToken(inputData);
        });

        // Verify the cause of the RuntimeException is an SQLException
        assertTrue(exception.getCause() instanceof SQLException);
        assertEquals("Simulated database error", exception.getCause().getMessage());

        // Verify interactions with mocks
        verify(mockPlaidDao, times(1)).exchangePublicToken(eq("test-public-token"));
        verify(mockUserRepository, times(1)).get(any());
    }




    @Test
    void testExchangePublicTokenIOException() throws IOException, PlaidException {
        ExchangePublicTokenInputData inputData = new ExchangePublicTokenInputData(
                "test-public-token",
                "test-client-id"
        );

        // Mock IOException
        when(mockPlaidDao.exchangePublicToken(any()))
                .thenThrow(new IOException("IO Error"));

        // Assert PlaidUseCaseException is thrown
        PlaidUseCaseException exception = assertThrows(PlaidUseCaseException.class, () -> {
            interactor.exchangePublicToken(inputData);
        });

        // Assert exception message
        assertTrue(exception.getMessage().contains("IO Error while exchanging Public Token"));
        verify(mockPlaidDao, times(1)).exchangePublicToken(eq("test-public-token"));
    }

    @Test
    void testExchangePublicTokenPlaidException() throws IOException, PlaidException {
        ExchangePublicTokenInputData inputData = new ExchangePublicTokenInputData(
                "test-public-token",
                "test-client-id"
        );

        // Mock PlaidException
        when(mockPlaidDao.exchangePublicToken(any()))
                .thenThrow(new PlaidException("Plaid API Error"));

        // Assert PlaidUseCaseException is thrown
        PlaidUseCaseException exception = assertThrows(PlaidUseCaseException.class, () -> {
            interactor.exchangePublicToken(inputData);
        });

        // Assert exception message
        assertTrue(exception.getMessage().contains("Failed to exchange Public Token"));
        verify(mockPlaidDao, times(1)).exchangePublicToken(eq("test-public-token"));
    }


}

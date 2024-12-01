package osiris.use_case.plaid;

import java.io.IOException;

import osiris.data_access.PlaidDataAccessObject;

/**
 * Interface for BankAccount data access operations.
 */
public interface UserPlaidDataAccessInterface {

    /**
     * Creates a link token for the Plaid API.
     *
     * @param clientName The name of the client.
     * @param countryCodes The country codes for the client.
     * @param language The language for the client.
     * @param userClientId The user client ID.
     * @param products The products for the client.
     * @return A response containing the link token.
     * @throws Exception If an error occurs while creating the link token.
     */
    PlaidDataAccessObject.LinkTokenResponse createLinkToken(String clientName, String[] countryCodes, String language,
                                                            String userClientId, String[] products) throws Exception;

    /**
     * Exchanges a public token for an access token.
     *
     * @param publicToken The public token to exchange.
     * @return A response containing the access token.
     * @throws IOException If an error occurs while exchanging the public token.
     */
    PlaidDataAccessObject.ExchangeTokenResponse exchangePublicToken(String publicToken) throws IOException;
}

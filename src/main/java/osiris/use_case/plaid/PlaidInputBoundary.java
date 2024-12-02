package osiris.use_case.plaid;

import osiris.utility.exceptions.PlaidUseCaseException;

/**
 * Interface defining the Plaid Interactor's use cases.
 */
public interface PlaidInputBoundary {

    /**
     * Creates a Plaid Link Token.
     *
     * @param inputData The input data required to create a link token.
     * @return The output data containing the link token.
     * @throws PlaidUseCaseException If an error occurs during the use case execution.
     */
    CreateLinkTokenOutputData createLinkToken(CreateLinkTokenInputData inputData) throws PlaidUseCaseException;

    /**
     * Exchanges a Public Token for an Access Token.
     *
     * @param inputData The input data required to exchange the public token.
     * @return The output data containing the access token and item ID.
     * @throws PlaidUseCaseException If an error occurs during the use case execution.
     */
    ExchangePublicTokenOutputData exchangePublicToken(ExchangePublicTokenInputData inputData) throws PlaidUseCaseException;
}

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

    /**
     * Create an Asset Report.
     *
     * @param accessToken The access token for the user's bank account.
     * @param daysRequested Number of days of data to include in the report.
     * @return The asset report token.
     * @throws PlaidUseCaseException If an error occurs while creating the report.
     */
    String createAssetReport(String accessToken, int daysRequested) throws PlaidUseCaseException;

    /**
     * Retrieve an Asset Report in JSON format.
     *
     * @param assetReportToken The token for the generated asset report.
     * @return The asset report as a JSON string.
     * @throws PlaidUseCaseException If an error occurs while retrieving the report.
     */
    String getAssetReport(String assetReportToken) throws PlaidUseCaseException;

    /**
     * Retrieve an Asset Report in PDF format.
     *
     * @param assetReportToken The token for the generated asset report.
     * @return The asset report as a byte array representing a PDF file.
     * @throws PlaidUseCaseException If an error occurs while retrieving the report.
     */
    byte[] getAssetReportPdf(String assetReportToken) throws PlaidUseCaseException;
}

package osiris.use_case.asset_report;

import osiris.utility.exceptions.PlaidUseCaseException;

public interface AssetReportInputBoundary {

    /**
     * Create an Asset Report.
     *
     * @param accessToken The access token for the user's bank account.
     * @param daysRequested The number of days for the report.
     * @return The asset report token.
     * @throws PlaidUseCaseException If an error occurs during creation.
     */
    String createAssetReport(String accessToken, int daysRequested) throws PlaidUseCaseException;

    /**
     * Retrieve an Asset Report in JSON format.
     *
     * @param assetReportToken The asset report token.
     * @return The asset report JSON.
     * @throws PlaidUseCaseException If an error occurs during retrieval.
     */
    String getAssetReport(String assetReportToken) throws PlaidUseCaseException;

    /**
     * Retrieve an Asset Report as a PDF.
     *
     * @param assetReportToken The asset report token.
     * @return The asset report PDF as a byte array.
     * @throws PlaidUseCaseException If an error occurs during retrieval.
     */
    byte[] getAssetReportPdf(String assetReportToken) throws PlaidUseCaseException;
}

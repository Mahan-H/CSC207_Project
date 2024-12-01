package osiris.interface_adapter.plaid;

import osiris.use_case.plaid.PlaidInputBoundary;
import osiris.use_case.plaid.CreateLinkTokenInputData;
import osiris.use_case.plaid.ExchangePublicTokenInputData;
import osiris.use_case.plaid.CreateLinkTokenOutputData;
import osiris.use_case.plaid.ExchangePublicTokenOutputData;
import osiris.utility.exceptions.PlaidUseCaseException;
import osiris.utility.data_transfer_objects.ExchangeTokenRequestDTO;
import osiris.utility.data_transfer_objects.LinkTokenRequestDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * REST Controller for Plaid-related operations.
 */
@RestController
@RequestMapping("/api/plaid")
public class PlaidController {

    private final PlaidInputBoundary plaidInteractor;

    @Autowired
    public PlaidController(PlaidInputBoundary plaidInteractor) {
        this.plaidInteractor = plaidInteractor;
    }

    /**
     * Endpoint to create a Plaid Link Token.
     *
     * @param linkTokenRequest The request body containing necessary parameters.
     * @return CreateLinkTokenOutputData containing the link token.
     */
    @PostMapping("/create-link-token")
    public ResponseEntity<CreateLinkTokenOutputData> createLinkToken(@RequestBody LinkTokenRequestDTO linkTokenRequest) throws PlaidUseCaseException {
        CreateLinkTokenInputData inputData = new CreateLinkTokenInputData(
                linkTokenRequest.getClientName(),
                linkTokenRequest.getCountryCodes(),
                linkTokenRequest.getLanguage(),
                linkTokenRequest.getUserClientId(),
                linkTokenRequest.getProducts()
        );
        CreateLinkTokenOutputData outputData = plaidInteractor.createLinkToken(inputData);
        return ResponseEntity.ok(outputData);
    }

    /**
     * Endpoint to exchange a Public Token for an Access Token.
     *
     * @param exchangeTokenRequest The request body containing the public token.
     * @return ExchangePublicTokenOutputData containing the access token and item ID.
     */
    @PostMapping("/exchange-public-token")
    public ResponseEntity<ExchangePublicTokenOutputData> exchangePublicToken(@RequestBody ExchangeTokenRequestDTO exchangeTokenRequest) throws PlaidUseCaseException {
        ExchangePublicTokenInputData inputData = new ExchangePublicTokenInputData(
                exchangeTokenRequest.getPublicToken(),
                exchangeTokenRequest.getUserClientId()
        );

        ExchangePublicTokenOutputData outputData = plaidInteractor.exchangePublicToken(inputData);
        return ResponseEntity.ok(outputData);
    }

    /**
     * Endpoint to create an Asset Report.
     *
     * @param requestBody The request body containing access token and days requested.
     * @return The asset report token.
     */
    @PostMapping("/asset-report/create")
    public ResponseEntity<String> createAssetReport(@RequestBody Map<String, Object> requestBody) throws PlaidUseCaseException {
        String accessToken = (String) requestBody.get("access_token");
        int daysRequested = (int) requestBody.get("days_requested");

        String assetReportToken = plaidInteractor.createAssetReport(accessToken, daysRequested);
        return ResponseEntity.ok(assetReportToken);
    }

    /**
     * Endpoint to retrieve an Asset Report.
     *
     * @param requestBody The request body containing the asset report token.
     * @return The asset report JSON.
     */
    @PostMapping("/asset-report/retrieve")
    public ResponseEntity<String> retrieveAssetReport(@RequestBody Map<String, String> requestBody) throws PlaidUseCaseException {
        String assetReportToken = requestBody.get("asset_report_token");

        String assetReport = plaidInteractor.retrieveAssetReport(assetReportToken);
        return ResponseEntity.ok(assetReport);
    }

}

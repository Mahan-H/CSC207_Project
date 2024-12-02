package osiris.interface_adapter.plaid;

import org.springframework.http.*;
import osiris.use_case.plaid.PlaidInputBoundary;
import osiris.use_case.plaid.CreateLinkTokenInputData;
import osiris.use_case.plaid.ExchangePublicTokenInputData;
import osiris.use_case.plaid.CreateLinkTokenOutputData;
import osiris.use_case.plaid.ExchangePublicTokenOutputData;
import osiris.utility.exceptions.PlaidUseCaseException;
import osiris.utility.data_transfer_objects.ExchangeTokenRequestDTO;
import osiris.utility.data_transfer_objects.LinkTokenRequestDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    // Create Asset Report
    @PostMapping("/asset-report/create")
    public ResponseEntity<String> createAssetReport(@RequestBody String accessToken) {
        try {
            String assetReportToken = plaidInteractor.createAssetReport(accessToken, 90);
            return ResponseEntity.ok(assetReportToken);
        } catch (PlaidUseCaseException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error creating asset report: " + e.getMessage());
        }
    }

    // Get Asset Report (JSON)
    @GetMapping("/asset-report/get")
    public ResponseEntity<String> getAssetReport(@RequestParam String assetReportToken) {
        try {
            String assetReport = plaidInteractor.getAssetReport(assetReportToken);
            return ResponseEntity.ok(assetReport);
        } catch (PlaidUseCaseException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error retrieving asset report: " + e.getMessage());
        }
    }

    // Get Asset Report as PDF
    @GetMapping("/asset-report/pdf")
    public ResponseEntity<byte[]> getAssetReportPdf(@RequestParam String assetReportToken) {
        try {
            byte[] pdfData = plaidInteractor.getAssetReportPdf(assetReportToken);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.builder("attachment").filename("asset_report.pdf").build());

            return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);
        } catch (PlaidUseCaseException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
}

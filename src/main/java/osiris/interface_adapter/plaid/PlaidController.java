package osiris.interface_adapter.plaid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import osiris.use_case.plaid.CreateLinkTokenInputData;
import osiris.use_case.plaid.CreateLinkTokenOutputData;
import osiris.use_case.plaid.ExchangePublicTokenInputData;
import osiris.use_case.plaid.ExchangePublicTokenOutputData;
import osiris.use_case.plaid.PlaidInputBoundary;
import osiris.utility.data_transfer_objects.ExchangeTokenRequestDataTransferObject;
import osiris.utility.data_transfer_objects.LinkTokenRequestDataTransferObject;
import osiris.utility.exceptions.PlaidUseCaseException;

import java.sql.SQLException;

/**
 * REST Controller for Plaid-related operations.
 */
@RestController
@RequestMapping("/api/plaid")
public class PlaidController {

    private final PlaidInputBoundary plaidInteractor;
    private String email;
    private String password;

    @Autowired
    public PlaidController(PlaidInputBoundary plaidInteractor) {
        this.plaidInteractor = plaidInteractor;
    }

    /**
     * Endpoint to create a Plaid Link Token.
     *
     * @param linkTokenRequest The request body containing necessary parameters.
     * @return CreateLinkTokenOutputData containing the link token.
     * @throws Exception If an error occurs while creating the link token.
     */
    @PostMapping("/create-link-token")
    public ResponseEntity<CreateLinkTokenOutputData> createLinkToken(
            @RequestBody LinkTokenRequestDataTransferObject linkTokenRequest)
            throws Exception {

        final CreateLinkTokenInputData inputData = new CreateLinkTokenInputData(
                linkTokenRequest.getClientName(),
                linkTokenRequest.getCountryCodes(),
                linkTokenRequest.getLanguage(),
                linkTokenRequest.getUserClientId(),
                linkTokenRequest.getProducts()
        );

        final CreateLinkTokenOutputData outputData = plaidInteractor.createLinkToken(inputData);
        return ResponseEntity.ok(outputData);
    }

    /**
     * Endpoint to exchange a Public Token for an Access Token.
     *
     * @param exchangeTokenRequest The request body containing the public token.
     * @return ExchangePublicTokenOutputData containing the access token and item ID.
     * @throws PlaidUseCaseException If an error occurs while exchanging the public token.
     * @throws SQLException If an error occurs while exchanging the public token.
     */
    @PostMapping("/exchange-public-token")
    public ResponseEntity<ExchangePublicTokenOutputData> exchangePublicToken(
            @RequestBody ExchangeTokenRequestDataTransferObject exchangeTokenRequest)
            throws PlaidUseCaseException, SQLException {

        final ExchangePublicTokenInputData inputData = new ExchangePublicTokenInputData(
                exchangeTokenRequest.getPublicToken(),
                exchangeTokenRequest.getUserClientId()
        );
        final ExchangePublicTokenOutputData outputData =
                plaidInteractor.exchangePublicToken(inputData);

        return ResponseEntity.ok(outputData);
    }
}

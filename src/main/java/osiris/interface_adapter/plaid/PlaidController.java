package osiris.interface_adapter.plaid;

import osiris.entity.BankAccount;
import osiris.view.PlaidException;
import osiris.use_case.plaid.PlaidService;
import osiris.use_case.plaid.PlaidService.ExchangeTokenResponse;
import osiris.use_case.plaid.PlaidService.LinkTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/plaid")
public class PlaidController {

    private final PlaidService plaidService;

    @Autowired
    public PlaidController(PlaidService plaidService) {
        this.plaidService = plaidService;
    }

    /**
     * Endpoint to create a Plaid Link Token.
     *
     * @param linkTokenRequest The request body containing necessary parameters.
     * @return LinkTokenResponse containing the link token.
     */
    @PostMapping("/create-link-token")
    public ResponseEntity<?> createLinkToken(@RequestBody LinkTokenRequest linkTokenRequest) {
        try {
            LinkTokenResponse response = plaidService.createLinkToken(
                    linkTokenRequest.getClientName(),
                    linkTokenRequest.getCountryCodes(),
                    linkTokenRequest.getLanguage(),
                    linkTokenRequest.getUserClientId(),
                    linkTokenRequest.getProducts()
            );
            return ResponseEntity.ok(response);
        } catch (PlaidException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    /**
     * Endpoint to exchange a Public Token for an Access Token.
     *
     * @param exchangeTokenRequest The request body containing the public token.
     * @return ExchangeTokenResponse containing the access token and item ID.
     */
    @PostMapping("/exchange-public-token")
    public ResponseEntity<?> exchangePublicToken(@RequestBody ExchangeTokenRequest exchangeTokenRequest) {
        try {
            ExchangeTokenResponse response = plaidService.exchangePublicToken(exchangeTokenRequest.getPublicToken());
            BankAccount bankAccount = new BankAccount(
                    exchangeTokenRequest.getUserClientId(),
                    response.access_token,
                    response.item_id
            );

            return ResponseEntity.ok(response);
        } catch (PlaidException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    /**
     * Data Transfer Object for Link Token Requests.
     */
    public static class LinkTokenRequest {
        private String clientName;
        private String[] countryCodes;
        private String language;
        private String userClientId;
        private String[] products;

        public String getClientName() {
            return clientName;
        }

        public void setClientName(String clientName) {
            this.clientName = clientName;
        }

        public String[] getCountryCodes() {
            return countryCodes;
        }

        public void setCountryCodes(String[] countryCodes) {
            this.countryCodes = countryCodes;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getUserClientId() {
            return userClientId;
        }

        public void setUserClientId(String userClientId) {
            this.userClientId = userClientId;
        }

        public String[] getProducts() {
            return products;
        }

        public void setProducts(String[] products) {
            this.products = products;
        }
    }

    /**
     * Data Transfer Object for Exchange Token Requests.
     */
    public static class ExchangeTokenRequest {
        private String publicToken;
        private String userClientId;

        public String getPublicToken() {
            return publicToken;
        }

        public void setPublicToken(String publicToken) {
            this.publicToken = publicToken;
        }

        public String getUserClientId() {
            return userClientId;
        }

        public void setUserClientId(String userClientId) {
            this.userClientId = userClientId;
        }
    }
}

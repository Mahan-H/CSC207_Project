package osiris.use_case.plaid;

/**
 * Input data for exchanging a Public Token for an Access Token.
 */
public class ExchangePublicTokenInputData {
    private final String publicToken;
    private final String userClientId;

    public ExchangePublicTokenInputData(String publicToken, String userClientId) {
        this.publicToken = publicToken;
        this.userClientId = userClientId;
    }

    public String getPublicToken() {
        return publicToken;
    }

    public String getUserClientId() {
        return userClientId;
    }
}

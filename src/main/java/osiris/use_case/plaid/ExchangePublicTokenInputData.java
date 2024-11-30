package osiris.use_case.plaid;

/**
 * Input data for exchanging a Public Token for an Access Token.
 */
public class ExchangePublicTokenInputData {
    private final String publicToken;
    private final String userClientId;
    private final String username;
    private final String password;

    public ExchangePublicTokenInputData(String publicToken, String userClientId, String username, String password) {
        this.publicToken = publicToken;
        this.userClientId = userClientId;
        this.username = username;
        this.password = password;
    }

    public String getPublicToken() {
        return publicToken;
    }

    public String getUserClientId() {
        return userClientId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

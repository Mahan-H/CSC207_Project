package osiris.utility.data_transfer_objects;

/**
 * Data Transfer Object for Exchange Token Requests.
 */
public class ExchangeTokenRequestDataTransferObject {
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

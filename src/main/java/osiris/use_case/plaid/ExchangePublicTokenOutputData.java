package osiris.use_case.plaid;

import com.google.gson.annotations.SerializedName;

/**
 * Output data after exchanging a Public Token for an Access Token.
 */
public class ExchangePublicTokenOutputData {

    @SerializedName("access_token")
    private final String accessToken;

    @SerializedName("item_id")
    private final String itemId;

    @SerializedName("request_id")
    private final String requestId;

    public ExchangePublicTokenOutputData(String accessToken, String itemId, String requestId) {
        this.accessToken = accessToken;
        this.itemId = itemId;
        this.requestId = requestId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getItemId() {
        return itemId;
    }

    public String getRequestId() {
        return requestId;
    }

    @Override
    public String toString() {
        return "ExchangePublicTokenOutputData{"
                +
                "accessToken='" + accessToken + '\''
                +
                ", itemId='" + itemId + '\''
                +
                ", requestId='" + requestId + '\''
                +
                '}';
    }
}

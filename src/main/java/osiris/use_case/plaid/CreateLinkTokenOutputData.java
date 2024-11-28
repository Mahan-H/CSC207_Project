package osiris.use_case.plaid;

import com.google.gson.annotations.SerializedName;

/**
 * Output data after creating a Plaid Link Token.
 */
public class CreateLinkTokenOutputData {

    @SerializedName("link_token")
    private final String linkToken;

    @SerializedName("request_id")
    private final String requestId;

    public CreateLinkTokenOutputData(String linkToken, String requestId) {
        this.linkToken = linkToken;
        this.requestId = requestId;
    }

    public String getLinkToken() {
        return linkToken;
    }

    public String getRequestId() {
        return requestId;
    }
}

package osiris.data_access;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.plaid.client.model.Transaction;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import osiris.use_case.plaid.UserPlaidDataAccessInterface;
import osiris.utility.exceptions.PlaidException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;


@Repository
public class PlaidDataAccessObject implements UserPlaidDataAccessInterface {

    private static final MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");

    private final OkHttpClient client;
    private final Gson gson;

    @Value("${plaid.client_id}")
    private String clientId;

    @Value("${plaid.secret}")
    private String secret;

    @Value("${plaid.environment}")
    private String environment;

    public PlaidDataAccessObject() {
        this.client = new OkHttpClient();
        this.gson = new Gson();
    }

    /**
     * Determines the Plaid API base URL based on the environment.
     *
     * @param endpoint Specific Plaid API endpoint.
     * @return Full URL to the Plaid API endpoint.
     */
    private String getPlaidUrl(String endpoint) {
        switch (environment.toLowerCase()) {
            case "development":
                return "https://development.plaid.com/" + endpoint;
            case "production":
                return "https://production.plaid.com/" + endpoint;
            case "sandbox":
            default:
                return "https://sandbox.plaid.com/" + endpoint;
        }
    }

    /**
     * Sends a POST request to the specified Plaid endpoint with the given JSON body.
     *
     * @param endpoint   The specific Plaid API endpoint.
     * @param jsonBody   The JSON body to send in the request.
     * @return The JSON response from Plaid as a string.
     * @throws IOException If an I/O error occurs during the API call.
     * @throws PlaidException If the Plaid API returns an unsuccessful response.
     */
    private String postToPlaid(String endpoint, JsonObject jsonBody) throws IOException {
        String url = getPlaidUrl(endpoint);

        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, gson.toJson(jsonBody));

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new PlaidException("Plaid API Error: " + response.code() + " - " + response.message());
            }

            return response.body().string();
        }
    }

    /**
     * Creates a Link Token by communicating with Plaid's API.
     *
     * @param clientName   Name of your application.
     * @param countryCodes Array of country codes (e.g., ["US"]).
     * @param language     Language code (e.g., "en").
     * @param userClientId Unique identifier for the user in your system.
     * @param products     Array of products (e.g., ["auth", "transactions"]).
     * @return LinkTokenResponse containing the link token.
     * @throws IOException If an I/O error occurs during the API call.
     */
    public LinkTokenResponse createLinkToken(String clientName, String[] countryCodes, String language,
                                             String userClientId, String[] products) throws IOException {
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("client_id", clientId);
        requestBody.addProperty("secret", secret);
        requestBody.addProperty("client_name", clientName);
        requestBody.addProperty("language", language);
        requestBody.add("country_codes", gson.toJsonTree(countryCodes));

        JsonObject user = new JsonObject();
        user.addProperty("client_user_id", userClientId);
        requestBody.add("user", user);

        requestBody.add("products", gson.toJsonTree(products));

        String responseBody = postToPlaid("link/token/create", requestBody);
        return gson.fromJson(responseBody, LinkTokenResponse.class);
    }

    /**
     * Exchanges a Public Token for an Access Token.
     *
     * @param publicToken The public token received from Plaid Link.
     * @return ExchangeTokenResponse containing the access token and item ID.
     * @throws IOException If an I/O error occurs during the API call.
     */
    public ExchangeTokenResponse exchangePublicToken(String publicToken) throws IOException {
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("client_id", clientId);
        requestBody.addProperty("secret", secret);
        requestBody.addProperty("public_token", publicToken);

        String responseBody = postToPlaid("item/public_token/exchange", requestBody);
        return gson.fromJson(responseBody, ExchangeTokenResponse.class);
    }


    /**
     * Represents the response from Plaid when creating a Link Token.
     */
    public static class LinkTokenResponse {
        public String link_token;
        public String request_id;

        public LinkTokenResponse(String testLinkToken, String testRequestId) {
            this.link_token = testLinkToken;
            this.request_id = testRequestId;
        }
    }

    /**
     * Represents the response from Plaid when exchanging a Public Token.
     */
    public static class ExchangeTokenResponse {
        public String access_token;
        public String item_id;
        public String request_id;

        public ExchangeTokenResponse(String testAccessToken, String testItemId, String testRequestId) {
            this.access_token = testAccessToken;
            this.item_id = testItemId;
            this.request_id = testRequestId;
        }
    }

    /**
     * Represents the response from Plaid when exchanging a access Token for transactions.
     * @param accessToken   Name of your token.
     * @return fetchTransactions containing the transactions.
     * @throws IOException If an I/O error occurs during the API call.
     */

    public String fetchTransactions(String accessToken) throws IOException {
        final LocalDate startDate = LocalDate.now().minusDays(30);
        final LocalDate endDate = LocalDate.now();

        final JsonObject requestBody = new JsonObject();
        requestBody.addProperty("client_id", clientId);
        requestBody.addProperty("secret", secret);
        requestBody.addProperty("access_token", accessToken);
        requestBody.addProperty("start_date", String.valueOf(startDate));
        requestBody.addProperty("end_date", String.valueOf(endDate));

        final RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, gson.toJson(requestBody));
        final Request request = new Request.Builder()
                .url("https://sandbox.plaid.com/transactions/get")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Plaid API Error: " + response.code() + " - " + response.message());
            }
            return response.body().string();
        }
    }
}

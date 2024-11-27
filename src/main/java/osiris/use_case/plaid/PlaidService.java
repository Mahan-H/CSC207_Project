package osiris.use_case.plaid;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import osiris.view.PlaidException;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PlaidService {

    private static final MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");

    private final OkHttpClient client;
    private final Gson gson;

    @Value("${plaid.client_id}")
    private String clientId;

    @Value("${plaid.secret}")
    private String secret;

    @Value("${plaid.environment}")
    private String environment;

    public PlaidService() {
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
        String url = getPlaidUrl("link/token/create");

        // Build JSON payload
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

        // Create RequestBody
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, gson.toJson(requestBody));

        // Build the HTTP request
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();

        // Execute the request
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new PlaidException("Plaid API Error: " + response.code() + " - " + response.message());
            }

            String responseBody = response.body().string();
            return gson.fromJson(responseBody, LinkTokenResponse.class);
        } catch (IOException e) {
            throw new PlaidException("Failed to create Link Token", e);
        }
    }

    /**
     * Exchanges a Public Token for an Access Token.
     *
     * @param publicToken The public token received from Plaid Link.
     * @return ExchangeTokenResponse containing the access token and item ID.
     * @throws IOException If an I/O error occurs during the API call.
     */
    public ExchangeTokenResponse exchangePublicToken(String publicToken) throws IOException {
        String url = getPlaidUrl("item/public_token/exchange");

        // Build JSON payload
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("client_id", clientId);
        requestBody.addProperty("secret", secret);
        requestBody.addProperty("public_token", publicToken);

        // Create RequestBody
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, gson.toJson(requestBody));

        // Build the HTTP request
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();

        // Execute the request
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new PlaidException("Plaid API Error: " + response.code() + " - " + response.message());
            }

            String responseBody = response.body().string();
            return gson.fromJson(responseBody, ExchangeTokenResponse.class);
        } catch (IOException e) {
            throw new PlaidException("Failed to exchange Public Token", e);
        }
    }

    /**
     * Represents the response from Plaid when creating a Link Token.
     */
    public static class LinkTokenResponse {
        public String link_token;
        public String request_id;
        // Add other fields as necessary
    }

    /**
     * Represents the response from Plaid when exchanging a Public Token.
     */
    public static class ExchangeTokenResponse {
        public String access_token;
        public String item_id;
        public String request_id;
        // Add other fields as necessary
    }
}

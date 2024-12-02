package use_case.grabtransactions;

import com.google.gson.GsonBuilder;
import osiris.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.plaid.client.model.Transaction;
import okhttp3.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import osiris.OffsetDateTimeAdapter;
import osiris.data_access.PlaidDataAccessObject;
import osiris.utility.exceptions.PlaidException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlaidDataAccessObjectTest {

    private PlaidDataAccessObject plaidDAO;
    private OkHttpClient mockClient;

    @BeforeEach
    void setUp() {
        mockClient = mock(OkHttpClient.class);
        Gson mockGson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .registerTypeAdapter(OffsetDateTime.class, new OffsetDateTimeAdapter())
                .create();

        plaidDAO = new PlaidDataAccessObject();
        plaidDAO.client = mockClient;
        plaidDAO.gson = mockGson;

        plaidDAO.clientId = "67391a194ef4a3001a48f150";
        plaidDAO.secret = "fdb61466892e58bd9e82685d2d5673";
        plaidDAO.environment = "sandbox";
    }

    @Test
    void testFetchTransactions() throws IOException, PlaidException {
        // Mock response JSON
        JsonArray transactionsArray = new JsonArray();
        JsonObject transaction = new JsonObject();
        transaction.addProperty("transaction_id", "12345");
        transaction.addProperty("amount", 100.0);
        transaction.addProperty("name", "Test Transaction");
        transactionsArray.add(transaction);

        JsonObject responseJson = new JsonObject();
        responseJson.add("transactions", transactionsArray);

        String mockResponseBody = responseJson.toString();

        // Mock OkHttpClient behavior
        ResponseBody body = ResponseBody.create(mockResponseBody, MediaType.get("application/json"));
        Response mockResponse = new Response.Builder()
                .request(new Request.Builder().url("http://mock").build())
                .protocol(Protocol.HTTP_1_1)
                .code(200)
                .message("OK")
                .body(body)
                .build();

        Call mockCall = mock(Call.class);
        when(mockClient.newCall(any(Request.class))).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(mockResponse);

        // Perform the test
        List<Transaction> transactions = plaidDAO.fetchTransactions(
                "access-sandbox-1dc59c35-32f2-4885-8776-ba9e03196bfd");

        // Assertions
        assertNotNull(transactions);
        assertEquals(1, transactions.size());
        assertEquals("12345", transactions.get(0).getTransactionId());
        assertEquals(100.0, transactions.get(0).getAmount());
        assertEquals("Test Transaction", transactions.get(0).getName());
    }
}

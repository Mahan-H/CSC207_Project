package use_case.grabtransactions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.ResponseEntity;
import osiris.PlaidIntegrationApplication;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = PlaidIntegrationApplication.class)
public class PlaidIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testFetchTransactions() {
        String token = "your-valid-access-token"; // Replace with a valid token
        ResponseEntity<String> response = restTemplate.getForEntity("/fetch-transactions?token={token}", String.class, token);

        assertNotNull(response.getBody());
        assertTrue(response.getBody().contains("transactions"));
        System.out.println(response.getBody()); // Prints the JSON response
    }
}


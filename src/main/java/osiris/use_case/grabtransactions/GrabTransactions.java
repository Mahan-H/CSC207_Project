package osiris.use_case.grabtransactions;

import com.plaid.client.model.*;
import com.plaid.client.request.PlaidApi;
import osiris.entity.User;

import retrofit2.Response;

// Java Standard Imports
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class GrabTransactions implements GrabTransactionsInputBoundary {
    private final GrabTransactionUserDataAccessInterface userDataAccessObject;
    private final PlaidApi api;

    public GrabTransactions(GrabTransactionUserDataAccessInterface userDataAccessObject, PlaidApi api) {
        this.userDataAccessObject = userDataAccessObject;
        this.api = api;
    }

    @Override
    public void execute(GrabTransactionsInputData grabTransactionsInputData) throws IOException {
        User user = userDataAccessObject.get(grabTransactionsInputData.getUsername());
        String token = user.getAccessCode();

        LocalDate startDate = LocalDate.now().minusDays(30);
        LocalDate endDate = LocalDate.now();
        TransactionsGetRequestOptions options = new TransactionsGetRequestOptions()
                .includePersonalFinanceCategory(true);

        TransactionsGetRequest request = new TransactionsGetRequest()
                .accessToken(token)
                .startDate(startDate)
                .endDate(endDate)
                .options(options);

        Response<TransactionsGetResponse> response = api.transactionsGet(request).execute();

        assert response.body() != null;
        List<Transaction> transactions = new ArrayList<>(response.body().getTransactions());

        while (transactions.size() < response.body().getTotalTransactions()) {
            options = new TransactionsGetRequestOptions()
                    .offset(transactions.size())
                    .includePersonalFinanceCategory(true);

            request = new TransactionsGetRequest() // Note the removal of type declaration here
                    .accessToken(token)
                    .startDate(startDate)
                    .endDate(endDate)
                    .options(options);

            response = api.transactionsGet(request).execute(); // Note the removal of type declaration here
            assert response.body() != null;
            transactions.addAll(response.body().getTransactions());
        }
    }

}

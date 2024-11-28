package osiris.use_case.viewexpenses;

import com.plaid.client.model.*;
import osiris.entity.User;
import osiris.entity.UserFactory;
import osiris.data_access.DBUserDataAccessObject;
import javax.swing.text.View;
import osiris.data_access.PlaidDataAccessObject;

import com.plaid.client.request.PlaidApi;



// Java Standard Imports
import java.util.List;
import java.util.ArrayList;

// Java Standard Imports
import java.util.List;
import java.util.ArrayList;



public class ViewExpensesInteractor implements ViewExpensesInputBoundary{
    private final ViewExpensesUserDataAccessInterface userDataAccessObject;
    private final ViewExpensesOutputBoundary userPresenter;
    private final UserFactory userFactory;


    public ViewExpensesInteractor(ViewExpensesUserDataAccessInterface userDataAccessObject, ViewExpensesOutputBoundary userPresenter, UserFactory userFactory) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
        this.userFactory = userFactory;
    }


    @Override
    public void execute(ViewExpensesInputData viewExpensesInputData) {
              User user = userDataAccessObject.get(viewExpensesInputData.getUsername());
              String token = user.getAccessCode();
              String itemID = user.getItemID();

// Provide a cursor from your database if you've previously
// recieved one for the item leave null if this is your
// first sync call for this item. The first request will
// return a cursor.

        String cursor = database.getLatestCursorOrNull(itemID);

// New transaction updates since "cursor"
        List<Transaction> added = new ArrayList<>();
        List<Transaction> modified = new ArrayList<>();
        List<RemovedTransaction> removed = new ArrayList<>();
        boolean hasMore = true;
        TransactionsSyncRequestOptions options = new TransactionsSyncRequestOptions()
                .includePersonalFinanceCategory(true);

// Iterate through each page of new transaction updates for item
        while (hasMore) {
            TransactionsSyncRequest request = new TransactionsSyncRequest()
                    .accessToken(token)
                    .cursor(cursor)
                    .options(options);

            PlaidApi plaidClient = null;
            ProcessorTransactionsSyncResponse response = plaidClient.transactionsSync(request).execute();

            // Add this page of results
            added.addAll(response.getAdded());
            modified.addAll(response.getModified());
            removed.addAll(response.getRemoved());

            hasMore = response.getHasMore();

            // Update cursor to the next cursor
            cursor = response.getNextCursor();
        }
// Persist cursor and updated data
        database.applyUpdates(itemID, added, modified, removed, cursor);



    }

    @Override
    public void switchToHome() {

    }
}




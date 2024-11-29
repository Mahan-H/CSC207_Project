package osiris.use_case.grabtransactions;

import com.plaid.client.model.*;
import osiris.data_access.PlaidDataAccessObject;
import osiris.entity.User;

import osiris.utility.exceptions.PlaidException;
import osiris.utility.exceptions.PlaidUseCaseException;

// Java Standard Imports
import java.io.IOException;
import java.util.List;

//@Service
public class GrabTransactions implements GrabTransactionsInputBoundary {
    private final GrabTransactionUserDataAccessInterface userDataAccessObject;
    private final PlaidDataAccessObject plaidDao;

//    @Autowired
    public GrabTransactions(GrabTransactionUserDataAccessInterface userDataAccessObject, PlaidDataAccessObject plaidDao) {
        this.userDataAccessObject = userDataAccessObject;
        this.plaidDao = plaidDao;
    }

    @Override
    public GrabTransactionOutputData fetchTransactions(GrabTransactionsInputData grabTransactionsInputData) throws IOException, PlaidUseCaseException {
        try {
            User user = userDataAccessObject.get(grabTransactionsInputData.getUsername());
            String token = user.getAccessCode();
            List<Transaction> transactions = plaidDao.fetchTransactions(token);


            return new GrabTransactionOutputData(transactions);

        } catch (PlaidException e) {
            throw new PlaidUseCaseException("Failed to create transactions: " + e.getMessage(), e);
        } catch (IOException e) {
            throw new PlaidUseCaseException("IO Error while creating transactions", e);
        }
    }

}

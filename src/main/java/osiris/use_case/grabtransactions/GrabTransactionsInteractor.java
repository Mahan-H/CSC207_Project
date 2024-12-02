package osiris.use_case.grabtransactions;

import java.io.IOException;
import java.util.List;

import com.plaid.client.model.Transaction;
import osiris.data_access.PlaidDataAccessObject;
import osiris.entity.User;
import osiris.utility.exceptions.PlaidException;
import osiris.utility.exceptions.PlaidUseCaseException;

/**
 * The GrabTransaction Interactor.
 */
public class GrabTransactionsInteractor implements GrabTransactionsInputBoundary {
    private final GrabTransactionUserDataAccessInterface userDataAccessObject;
    private final PlaidDataAccessObject plaidDao;

    public GrabTransactionsInteractor(GrabTransactionUserDataAccessInterface userDataAccessObject,
                                      PlaidDataAccessObject plaidDao) {
        this.userDataAccessObject = userDataAccessObject;
        this.plaidDao = plaidDao;
    }

    @Override
    public GrabTransactionOutputData fetchTransactions(GrabTransactionsInputData grabTransactionsInputData)
            throws IOException, PlaidUseCaseException {
        try {
            final User user = userDataAccessObject.get(grabTransactionsInputData.getUsername());
            final String token = user.getAccessCode();
            final List<Transaction> transactions = plaidDao.fetchTransactions(token);

            return new GrabTransactionOutputData(transactions);

        }
        catch (PlaidException ex) {
            throw new PlaidUseCaseException("Failed to create transactions: " + ex.getMessage(), ex);
        }
        catch (IOException ex) {
            throw new PlaidUseCaseException("IO Error while creating transactions", ex);
        }
    }

}

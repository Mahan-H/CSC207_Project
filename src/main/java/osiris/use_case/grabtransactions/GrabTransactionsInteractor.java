package osiris.use_case.grabtransactions;

import java.io.IOException;

import osiris.data_access.PlaidDataAccessObject;
import osiris.entity.User;
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
            if (user == null) {
                throw new PlaidUseCaseException("User not found: " + grabTransactionsInputData.getUsername());
            }
            final String token = user.getAccessCode();

            final String transactions = plaidDao.fetchTransactions(token);
            return new GrabTransactionOutputData(transactions);

        }
        catch (PlaidUseCaseException ex) {
            throw new PlaidUseCaseException(ex.getMessage(), ex);
        }
        catch (IOException ex) {
            throw new IOException("IO Error while creating transactions", ex);
        }
    }

}

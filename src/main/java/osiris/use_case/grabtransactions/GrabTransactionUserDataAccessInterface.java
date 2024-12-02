package osiris.use_case.grabtransactions;

import osiris.entity.User;

/**
 * The GrabTransactions DataAccessInterface.
 */

public interface GrabTransactionUserDataAccessInterface {
    /**
     * Get method for username.
     * @param username the username to look for
     * @return the code
     */

    User get(String username);
}

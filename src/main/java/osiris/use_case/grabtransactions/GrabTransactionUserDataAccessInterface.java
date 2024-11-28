package osiris.use_case.grabtransactions;

import osiris.entity.User;

public interface GrabTransactionUserDataAccessInterface {
    /**
     * get method for username.
     * @param username the username to look for
     * @return the code
     */

    User get(String username);
}

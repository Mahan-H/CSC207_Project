package osiris.use_case.plaid;

import osiris.entity.User;

/**
 * DAO for the Signup Use Case.
 */
public interface PlaidDataBaseUserAccessObjectInterface {

    /**
     * Saves the user.
     * @param user the user to save
     */
    void save(User user);
}

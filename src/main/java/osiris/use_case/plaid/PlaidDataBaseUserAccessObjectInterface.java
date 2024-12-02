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

    /**
     * Sets the access code.
     * @param user the user to set the access code
     */
    void setAccessCode(User user);

    /**
     * Gets the user.
     * @param username the username of the user to get
     * @return the user
     */
    User get(String username);
}

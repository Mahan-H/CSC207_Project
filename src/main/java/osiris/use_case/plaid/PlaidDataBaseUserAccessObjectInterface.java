package osiris.use_case.plaid;

import java.sql.SQLException;

import osiris.entity.User;

/**
 * DAO for the Signup Use Case.
 */
public interface PlaidDataBaseUserAccessObjectInterface {

    /**
     * Sets the access code.
     * @param user the user to set the access code
     * @throws SQLException if there is an error setting the access code
     */
    void save(User user) throws SQLException;

    /**
     * Gets the user.
     * @param username the username of the user to get
     * @return the user
     */
    User get(String username);

    void changePassword(User newUser);
}

package osiris.use_case.login;

import osiris.entity.User;

/**
 * DAO for the Login Use Case.
 */
public interface LoginUserDataAccessInterface {

    /**
     * Checks if the given username exists.
     * @param username the username to look for
     * @return true if a user with the given username exists; false otherwise
     */
    boolean existsByName(String username);

    /**
     * Saves the user.
     * @param user the user to save
     */
    void save(User user);

    /**
     * Returns the user with the given username.
     * @param user the username to look up
     * @return the user with the given username
     */
    User get(String user);

    /**
     * Returns the username of the curren user of the application.
     * @return the username of the current user; null indicates that no one is logged into the application.
     */
    String getCurrentUser();

    /**
     * Sets the username indicating who is the current user of the application.
     * @param user the new current username; null to indicate that no one is currently logged into the application.
     */
    void setCurrentUser(String user);
}

package osiris.use_case.plaid;

import osiris.entity.BankAccount;

/**
 * Interface for BankAccount data access operations.
 */
public interface UserPlaidDataAccessInterface {
    /**
     * Retrieves a BankAccount by userClientId.
     *
     * @param userClientId The unique identifier for the user.
     * @return The BankAccount object.
     */
    BankAccount getUserByClientId(String userClientId);

    /**
     * Saves a new BankAccount.
     *
     * @param bankAccount The BankAccount object to save.
     */
    void saveUser(BankAccount bankAccount);

    /**
     * Checks if a BankAccount exists by userClientId.
     *
     * @param userClientId The unique identifier for the user.
     * @return True if exists, else false.
     */
    boolean existsByClientId(String userClientId);
}

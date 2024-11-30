package osiris.utility.exceptions;

/**
 * Exception thrown when an error occurs during a Plaid operation.
 */
public class PlaidException extends RuntimeException {

    public PlaidException(String message) {
        super(message);
    }

    public PlaidException(String message, Throwable cause) {
        super(message, cause);
    }
}

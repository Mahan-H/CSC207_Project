package osiris.utility.exceptions;

/**
 * Exception thrown when an error occurs during a Plaid use case.
 */
public class PlaidUseCaseException extends Exception {
    public PlaidUseCaseException(String message) {
        super(message);
    }

    public PlaidUseCaseException(String message, Throwable cause) {
        super(message, cause);
    }
}

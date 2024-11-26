package osiris.view;

public class PlaidException extends RuntimeException {
    public PlaidException(String message) {
        super(message);
    }

    public PlaidException(String message, Throwable cause) {
        super(message, cause);
    }
}

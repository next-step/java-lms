package nextstep.payments.exception;

public class InvalidPaymentException extends RuntimeException {

    public InvalidPaymentException() {
        super("Invalid payment");
    }

    public InvalidPaymentException(String message) {
        super(message);
    }

    public InvalidPaymentException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPaymentException(Throwable cause) {
        super(cause);
    }
}

package nextstep.courses.exception;

public class PaymentMisMatchException extends RuntimeException {

    public PaymentMisMatchException(String message) {
        super(message);
    }
}

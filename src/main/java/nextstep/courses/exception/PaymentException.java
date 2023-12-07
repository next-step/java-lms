package nextstep.courses.exception;

public class PaymentException extends IllegalArgumentException{
    public PaymentException(String message) {
        super(message);
    }
}

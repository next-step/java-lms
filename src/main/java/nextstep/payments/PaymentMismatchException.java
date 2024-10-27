package nextstep.payments;

public class PaymentMismatchException extends RuntimeException {

    public PaymentMismatchException(String message) {
        super(message);
    }
}

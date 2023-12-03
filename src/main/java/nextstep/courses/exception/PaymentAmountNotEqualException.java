package nextstep.courses.exception;

public class PaymentAmountNotEqualException extends IllegalArgumentException {

    private static final String MESSAGE = ExceptionMessage.PAYMENT_AMOUNT_NOT_EQUAL.getMessage();

    public PaymentAmountNotEqualException() {
        this(MESSAGE);
    }

    public PaymentAmountNotEqualException(String s) {
        super(s);
    }
}

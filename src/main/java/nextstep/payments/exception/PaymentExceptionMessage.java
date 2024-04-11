package nextstep.payments.exception;

public enum PaymentExceptionMessage {

    PAYMENT_AMOUNT_EXISTS_EXCEPTION("결제 금액이 존재합니다.");

    private final String message;

    PaymentExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

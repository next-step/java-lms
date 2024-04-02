package nextstep.payments.domain;

public enum PaymentState {
    PENDING("대기중"),
    PAYMENT_COMPLETE("결제완료"),
    CANCELLED("취소"),
    COMPLETE("완료");

    private final String text;

    PaymentState(String text) {
        this.text = text;
    }

    public boolean isPaymentComplete() {
        return this == PAYMENT_COMPLETE;
    }
}

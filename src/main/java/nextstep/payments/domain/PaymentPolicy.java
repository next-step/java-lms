package nextstep.payments.domain;

public class PaymentPolicy {

    private final PaymentType paymentType;
    private final long fee;
    private final int enrollmentLimit;

    public PaymentPolicy(PaymentType paymentType, long fee, int enrollmentLimit) {
        if (paymentType == PaymentType.PAID && enrollmentLimit <= 0) {
            throw new IllegalArgumentException("유료 강의는 최대 수강 인원이 0보다 커야 합니다.");
        }
        if (paymentType == PaymentType.FREE && enrollmentLimit != 0) {
            throw new IllegalArgumentException("무료 강의는 최대 수강 인원이 없어야 하므로 0이어야 합니다.");
        }
        this.paymentType = paymentType;
        this.fee = fee;
        this.enrollmentLimit = enrollmentLimit;
    }

    public boolean isPaidPayment() {
        return paymentType == PaymentType.PAID;
    }

    public boolean isFreePayment() {
        return paymentType == PaymentType.FREE;
    }

    public int enrollmentLimit() {
        return enrollmentLimit;
    }

    public void validateEnrollment(long amount) {
        if (!isPaidPayment()) return;

        if (amount != fee) {
            throw new IllegalArgumentException("결제 금액이 수강료와 일치하지 않습니다.");
        }
    }
}

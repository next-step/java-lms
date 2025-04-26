package nextstep.payments.domain;

public class PaidPaymentPolicy implements PaymentPolicy {

    private final Long fee;
    private final Integer enrollmentLimit;

    public PaidPaymentPolicy(long fee, int enrollmentLimit) {
        if (fee <= 0) {
            throw new IllegalArgumentException("유료강의의 수강료는 0보다 커야 합니다. 수강료=" + fee);
        }

        if (enrollmentLimit <= 0) {
            throw new IllegalArgumentException("유료 강의는 최대 수강 인원이 0보다 커야 합니다. 최대수강인원=" + enrollmentLimit);
        }
        this.fee = fee;
        this.enrollmentLimit = enrollmentLimit;
    }

    @Override
    public long fee() {
        return this.fee;
    }

    @Override
    public int enrollmentLimit() {
        return enrollmentLimit;
    }

    @Override
    public boolean canEnroll(int studentCount) {
        return studentCount < enrollmentLimit();
    }

    @Override
    public void validateEnrollment(long amount) {
        if (amount != fee) {
            throw new IllegalArgumentException("결제 금액이 수강료와 일치하지 않습니다.");
        }
    }
}

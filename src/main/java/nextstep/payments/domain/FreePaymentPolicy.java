package nextstep.payments.domain;

public class FreePaymentPolicy implements PaymentPolicy {

    public FreePaymentPolicy() {
    }

    @Override
    public long fee() {
        return 0;
    }

    @Override
    public int enrollmentLimit() {
        return 0;
    }

    @Override
    public boolean canEnroll(int studentCount) {
        return true;
    }

    @Override
    public void validateEnrollment(long amount) {
    }
}

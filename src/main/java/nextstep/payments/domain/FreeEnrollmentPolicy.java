package nextstep.payments.domain;

public class FreeEnrollmentPolicy implements EnrollmentPolicy {

    @Override
    public boolean canEnroll(int currentEnrolledCount, Payment payment) {
        return payment.isSameAmount(0L);
    }
}

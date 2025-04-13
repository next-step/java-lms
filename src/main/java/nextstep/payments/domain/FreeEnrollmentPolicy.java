package nextstep.payments.domain;

public class FreeEnrollmentPolicy implements EnrollmentPolicy {

    @Override
    public boolean canEnroll(int currentEnrolledCount, int paidAmount) {
        return paidAmount == 0;
    }
}

package nextstep.payments.domain;

public interface EnrollmentPolicy {
    boolean canEnroll(int currentEnrolledCount, int paidAmount);
}

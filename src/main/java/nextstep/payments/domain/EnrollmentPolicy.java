package nextstep.payments.domain;

public interface EnrollmentPolicy {
    boolean canEnroll(int currentEnrolledCount, Payment payment);
}

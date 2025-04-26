package nextstep.payments.domain;

public interface PaymentPolicy {
    long fee();
    int enrollmentLimit();
    void validateEnrollment(long amount);
    boolean canEnroll(int studentCount);
}
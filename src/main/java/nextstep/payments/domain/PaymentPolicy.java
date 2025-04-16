package nextstep.payments.domain;

public interface PaymentPolicy {
    void validateEnrollment(long amount);

    int enrollmentLimit();

    boolean canEnroll(int studentCount);
}
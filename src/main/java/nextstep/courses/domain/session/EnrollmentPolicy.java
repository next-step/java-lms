package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;

public class EnrollmentPolicy {
    private final SessionType sessionType;
    private final int capacity;
    private final long fee;

    public EnrollmentPolicy(SessionType sessionType, int capacity, long fee) {
        this.sessionType = sessionType;
        this.capacity = capacity;
        this.fee = fee;
    }

    public void validateEnrollment(Students students, Payment payment) {
        validateCapacity(students);
        validatePayment(payment);
    }

    private void validateCapacity(Students students) {
        if (students.isFull(capacity)) {
            throw new IllegalStateException();
        }
    }

    private void validatePayment(Payment payment) {
        if (sessionType != SessionType.PAID) {
            return;
        }
        if (payment == null || !payment.matchesFee(fee)) {
            throw new IllegalStateException();
        }
    }
}

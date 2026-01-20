package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;

public class EnrollmentPolicy {
    private final SessionType sessionType;
    private final int capacity;
    private final long fee;

    private EnrollmentPolicy(SessionType sessionType, int capacity, long fee) {
        this.sessionType = sessionType;
        this.capacity = capacity;
        this.fee = fee;
    }

    public SessionType getSessionType() {
        return sessionType;
    }

    public String getSessionTypeName() {
        return sessionType.name();
    }

    public int getCapacity() {
        return capacity;
    }

    public long getFee() {
        return fee;
    }


    public static EnrollmentPolicy free() {
        return new EnrollmentPolicy(SessionType.FREE, Integer.MAX_VALUE, 0L);
    }

    public static EnrollmentPolicy paid(int capacity, long fee) {
        return new EnrollmentPolicy(SessionType.PAID, capacity, fee);
    }

    public void validateEnrollment(Students students, Payment payment) {
        validateCapacity(students);
        validatePayment(payment);
    }

    private void validateCapacity(Students students) {
        if (sessionType == SessionType.FREE) {
            return;
        }
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

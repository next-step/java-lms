package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

public abstract class SessionType {
    protected int maxNumberOfEnrollment;
    protected long fee;

    public SessionType(int maxNumberOfEnrollment, long fee) {
        validateSessionTypeInput(maxNumberOfEnrollment, fee);
        this.maxNumberOfEnrollment = maxNumberOfEnrollment;
        this.fee = fee;
    }

    protected abstract void validateSessionTypeInput(int maxNumberOfEnrollment, long price);

    public abstract boolean isSessionFull(long currentNumberOfEnrollment);

    public abstract boolean isValidPayment(Payment payment);
}

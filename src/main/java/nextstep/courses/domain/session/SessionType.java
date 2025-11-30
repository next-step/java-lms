package nextstep.courses.domain.session;

public abstract class SessionType {

    public abstract boolean isFree();

    public abstract boolean isOverCapacity(int currentEnrollmentCount);

    public abstract boolean isValidPayment(Long paymentAmount);
}

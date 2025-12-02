package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;

public interface SessionType {

    public  boolean isFree();

    public  boolean isOverCapacity(int currentEnrollmentCount);

    public  boolean isValidPayment(Payment payment);
}

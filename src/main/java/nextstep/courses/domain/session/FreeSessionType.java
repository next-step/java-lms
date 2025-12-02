package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;

public class FreeSessionType implements SessionType {
    @Override
    public boolean isFree() {
        return true;
    }

    @Override
    public boolean isOverCapacity(int currentEnrollmentCount) {
        return false;
    }

    @Override
    public boolean isValidPayment(Payment payment) {
        return true;
    }
}

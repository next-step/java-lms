package nextstep.courses.domain.session;

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
    public boolean isValidPayment(Long paymentAmount) {
        return true;
    }
}

package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;

public class PaidSessionType implements SessionType {

    private final Integer maximumCapacity;
    private final Long fee;

    public PaidSessionType(Integer maximumCapacity, Long fee) {
        this.maximumCapacity = maximumCapacity;
        this.fee = fee;
    }


    @Override
    public boolean isFree() {
        return false;
    }

    @Override
    public boolean isOverCapacity(int currentEnrollmentCount) {
        if (isFree()) {
            return false;
        }
        return currentEnrollmentCount >= maximumCapacity;
    }

    @Override
    public boolean isValidPayment(Payment payment) {
        if (payment == null) {
            return false;
        }
        return payment.isAmountEquals(fee);
    }

    public int getMaxCapacity() {
        return maximumCapacity;
    }

    public long getFee() {
        return fee;
    }

}

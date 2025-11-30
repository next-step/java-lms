package nextstep.courses.domain.session;

public class PaidSessionType extends SessionType {

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
    public boolean isValidPayment(Long paymentAmount) {
        if (isFree()) {
            return true;
        }
        return paymentAmount != null && paymentAmount.equals(fee);
    }

    public int getMaxCapacity() {
        return maximumCapacity;
    }

    public long getFee() {
        return fee;
    }

}

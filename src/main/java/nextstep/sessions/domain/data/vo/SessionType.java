package nextstep.sessions.domain.data.vo;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.data.type.PayType;

public class SessionType {

    private final PayType payType;
    private long fee;
    private int capacity;

    public SessionType(PayType payType) {
        this.payType = payType;
    }

    public SessionType(PayType payType, long fee, int capacity) {
        this.payType = payType;
        this.fee = fee;
        this.capacity = capacity;
    }

    public boolean isPaid() {
        return payType.isPay();
    }

    public boolean isEnoughCapacity(int registrationCount) {
        return registrationCount < capacity;
    }

    public boolean isEqualPaidAmount(Payment payment) {
        return payment.isEqualAmount(fee);
    }
}

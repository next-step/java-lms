package nextstep.sessions.domain.data.vo;

import nextstep.sessions.domain.data.type.PayType;

public class SessionType {

    private final PayType payType;
    private final int fee;
    private final int capacity;

    public SessionType(PayType payType, int fee, int capacity) {
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
}

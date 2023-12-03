package nextstep.sessions.domain.data.vo;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.data.type.PaidType;
import nextstep.sessions.domain.exception.SessionsException;

public class SessionType {

    private final PayInfo payInfo;
    private final int capacity;

    public SessionType(PaidType paidType, long fee, int capacity) {
        this.payInfo = new PayInfo(paidType, fee);
        this.capacity = capacity;
    }

    public void validateSession(int registrationCount, Payment payment) {
        if (payInfo.isPaid()) {
            validateCapacity(registrationCount);
            payInfo.validatePayment(payment);
        }
    }

    private void validateCapacity(int registrationCount) {
        if (!isValidCapacity(registrationCount)) {
            throw new SessionsException("강의 최대 인원을 초과했습니다.");
        }
    }

    private boolean isValidCapacity(int registrationCount) {
        return registrationCount < capacity;
    }

    public PayInfo payInfo() {
        return payInfo;
    }

    public int capacity() {
        return capacity;
    }
}

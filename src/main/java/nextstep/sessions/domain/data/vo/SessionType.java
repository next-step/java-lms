package nextstep.sessions.domain.data.vo;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.data.type.PayType;
import nextstep.sessions.domain.exception.SessionsException;

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

    public void validatePaidSession(int registrationCount, Payment payment) {
        if (payType.isPaid()) {
            validateCapacity(registrationCount);
            validatePayment(payment);
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

    private void validatePayment(Payment payment) {
        if (!isValidPayment(payment)) {
            throw new SessionsException("수강료와 결제한 금액이 다릅니다.");
        }
    }

    private boolean isValidPayment(Payment payment) {
        return payment.isEqualAmount(fee);
    }

}

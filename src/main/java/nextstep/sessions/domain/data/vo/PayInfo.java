package nextstep.sessions.domain.data.vo;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.data.type.PaidType;
import nextstep.sessions.domain.exception.SessionsException;

public class PayInfo {

    private final PaidType paidType;
    private final long fee;

    public PayInfo(PaidType paidType, long fee) {
        this.paidType = paidType;
        this.fee = fee;
    }

    public void validatePayment(Payment payment) {
        if (!isValidPayment(payment)) {
            throw new SessionsException("수강료와 결제한 금액이 다릅니다.");
        }
    }

    private boolean isValidPayment(Payment payment) {
        return payment.isEqualAmount(fee);
    }

    public boolean isPaid() {
        return paidType.isPaid();
    }
}

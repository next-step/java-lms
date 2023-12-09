package nextstep.sessions.domain.data.session;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.data.session.PaidType;
import nextstep.sessions.domain.exception.SessionsException;

public class PayInfo {

    private final PaidType paidType;
    private final long fee;

    public PayInfo(PaidType paidType, long fee) {
        validateSessionFee(paidType, fee);
        this.paidType = paidType;
        this.fee = fee;
    }

    private void validateSessionFee(PaidType paidType, long fee) {
        if (fee < 0) {
            throw new SessionsException("수강료는 0 이상이어야 합니다.");
        }
        if (paidType.isPaid()) {
            validatePaidSessionFee(fee);
        }
        if (paidType.isFree()) {
            validateFreeSessionFee(fee);
        }
    }

    private void validatePaidSessionFee(long fee) {
        if (fee == 0) {
            throw new SessionsException("유료 강의는 수강료를 입력해야합니다.");
        }
    }

    private void validateFreeSessionFee(long fee) {
        if (fee > 0) {
            throw new SessionsException("무료 강의는 수강료를 입력할 수 없습니다.");
        }
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
        return paidType == PaidType.PAID;
    }

    public String paidTypeName() {
        return paidType.name();
    }

    public long fee() {
        return fee;
    }
}

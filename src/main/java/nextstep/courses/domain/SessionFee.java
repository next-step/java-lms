package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

public class SessionFee {
    private final Long fee;

    public SessionFee(Long fee) {
        validateNegative(fee);
        this.fee = fee;
    }

    private void validateNegative(Long fee) {
        if (fee < 0L) {
            throw new IllegalArgumentException("수강료는 0이상이어야 합니다.");
        }
    }

    public boolean canPurchase(Payment payment) {
        return payment.canPay(this.fee);
    }

    public boolean isFree() {
        return fee == 0L;
    }

    public Long getFee() {
        return fee;
    }
}

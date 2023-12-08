package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;

public class SessionFee {
    private static final String INVALID_FEE_MESSAGE = "강의 금액을 확인해주세요.";

    private final Long fee;

    public SessionFee(final Long fee) {
        if (fee < 0) {
            throw new IllegalArgumentException(INVALID_FEE_MESSAGE);
        }
        this.fee = fee;
    }

    public boolean isFree() {
        return fee == 0;
    }

    public boolean isNotEqualTo(final Payment payment) {
        return !fee.equals(payment.getAmount());
    }
}

package nextstep.courses.domain.enrollment;

import nextstep.payments.domain.Payment;

public class SessionFee {

    public static final Long FREE = 0L;

    private final Long sessionId;
    private final Long fee;

    public SessionFee(Long sessionId, Long fee) {
        this.sessionId = sessionId;
        this.fee = fee;
    }

    public boolean differentFrom(Payment payment) {
        return !fee.equals(payment.getAmount());
    }

    public Long get() {
        return fee;
    }
}

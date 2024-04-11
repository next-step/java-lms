package nextstep.courses.domain.enrollment;

import nextstep.payments.domain.Payment;

public class SessionFee {

    public static final Long FREE = 0L;

    private final Long id;
    private final Long sessionId;
    private final Long fee;

    public SessionFee(Long id, Long sessionId, Long fee) {
        this.id = id;
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

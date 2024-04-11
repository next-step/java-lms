package nextstep.courses.domain.enrollment;

import nextstep.courses.exception.SessionFeeMismatchException;
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

    public void sameAs(Payment payment) {
        if (!fee.equals(payment.getAmount())) {
            throw new SessionFeeMismatchException(this, payment);
        }
    }

    public Long get() {
        return fee;
    }
}

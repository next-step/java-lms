package nextstep.courses.domain.enrollment;

import nextstep.courses.exception.MismatchSessionFeeException;

public class SessionFee {

    private final Long id;
    private final Long sessionId;
    private final Long fee;

    public SessionFee(Long id, Long sessionId, Long fee) {
        this.id = id;
        this.sessionId = sessionId;
        this.fee = fee;
    }

    public void validatePaymentAmount(Long amount) {
        if (!sameAs(amount)) {
            throw new MismatchSessionFeeException(this, amount);
        }
    }

    private boolean sameAs(Long amount) {
        return fee.equals(amount);
    }

    public Long get() {
        return fee;
    }
}

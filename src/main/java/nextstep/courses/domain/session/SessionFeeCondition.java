package nextstep.courses.domain.session;

import nextstep.courses.exception.MismatchSessionFeeException;
import nextstep.courses.exception.SessionException;

public class SessionFeeCondition implements SessionEnrollmentCondition {

    private final SessionFee fee;
    private final Long paymentAmount;

    public SessionFeeCondition(SessionFee fee, Long paymentAmount) {
        this.fee = fee;
        this.paymentAmount = paymentAmount;
    }

    @Override
    public void satisfy() throws SessionException {
        if (!fee.sameAs(paymentAmount)) {
            throw new MismatchSessionFeeException(fee, paymentAmount);
        }
    }

}

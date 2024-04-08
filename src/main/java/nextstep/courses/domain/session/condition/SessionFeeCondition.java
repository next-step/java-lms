package nextstep.courses.domain.session.condition;

import nextstep.courses.domain.session.SessionFee;
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

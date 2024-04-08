package nextstep.courses.domain.session.condition;

import nextstep.courses.domain.session.SessionFee;
import nextstep.courses.exception.MismatchSessionFeeException;
import nextstep.courses.exception.SessionException;

public class SessionFeeCondition implements SessionCondition {

    private final SessionFee sessionFee;
    private final Long paymentAmount;

    public SessionFeeCondition(SessionFee sessionFee, Long paymentAmount) {
        this.sessionFee = sessionFee;
        this.paymentAmount = paymentAmount;
    }

    @Override
    public void satisfy() throws SessionException {
        if (!sessionFee.sameAs(paymentAmount)) {
            throw new MismatchSessionFeeException(sessionFee, paymentAmount);
        }
    }

}

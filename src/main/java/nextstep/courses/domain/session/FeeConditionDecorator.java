package nextstep.courses.domain.session;

import nextstep.courses.exception.MismatchSessionFeeException;
import nextstep.courses.exception.SessionException;
import nextstep.payments.domain.Payment;

public class FeeConditionDecorator extends SessionConditionDecorator {

    private final Payment payment;

    public FeeConditionDecorator(SessionCondition sessionCondition, Payment payment) {
        super(sessionCondition);
        this.payment = payment;
    }

    @Override
    public void canEnroll(Session session) throws SessionException {
        super.canEnroll(session);
        if (!session.matchFee(payment.getAmount())) {
            throw new MismatchSessionFeeException(session.getFee(), payment.getAmount());
        }
    }

}

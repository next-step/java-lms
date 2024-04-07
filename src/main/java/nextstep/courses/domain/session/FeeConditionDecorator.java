package nextstep.courses.domain.session;

import nextstep.courses.exception.MismatchSessionFeeException;
import nextstep.courses.exception.SessionConditionException;
import nextstep.payments.domain.Payment;

public class FeeConditionDecorator extends SessionConditionDecorator {

    private final Payment payment;

    public FeeConditionDecorator(SessionCondition sessionCondition, Payment payment) {
        super(sessionCondition);
        this.payment = payment;
    }

    @Override
    public void canEnroll(Session session) throws SessionConditionException {
        super.canEnroll(session);
        if (!session.matchFee(payment.getAmount())) {
            throw new MismatchSessionFeeException(session.getFee(), payment.getAmount());
        }
    }

}

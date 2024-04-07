package nextstep.courses.domain.session;

import nextstep.courses.exception.ExceedSessionCapacityException;
import nextstep.courses.exception.SessionException;

public class CapacityConditionDecorator extends SessionConditionDecorator {

    public CapacityConditionDecorator(SessionCondition sessionCondition) {
        super(sessionCondition);
    }

    @Override
    public void canEnroll(Session session) throws SessionException {
        super.canEnroll(session);
        if (!session.hasCapacity()) {
            throw new ExceedSessionCapacityException(session.getCapacity());
        }
    }

}

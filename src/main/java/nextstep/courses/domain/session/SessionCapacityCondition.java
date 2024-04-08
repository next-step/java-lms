package nextstep.courses.domain.session;

import nextstep.courses.exception.ExceedSessionCapacityException;
import nextstep.courses.exception.SessionException;

public class SessionCapacityCondition implements SessionEnrollmentCondition {

    @Override
    public void isSatisfied(Session session) throws SessionException {
        if (!session.hasCapacity()) {
            throw new ExceedSessionCapacityException(session.getCapacity());
        }
    }
}

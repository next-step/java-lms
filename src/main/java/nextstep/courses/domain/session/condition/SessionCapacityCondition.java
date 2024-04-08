package nextstep.courses.domain.session.condition;

import nextstep.courses.domain.session.SessionCapacity;
import nextstep.courses.exception.ExceedSessionCapacityException;
import nextstep.courses.exception.SessionException;

public class SessionCapacityCondition implements SessionCondition {

    private final SessionCapacity capacity;

    public SessionCapacityCondition(SessionCapacity capacity) {
        this.capacity = capacity;
    }

    @Override
    public void satisfy() throws SessionException {
        if (!capacity.hasCapacity()) {
            throw new ExceedSessionCapacityException(capacity);
        }
    }

}

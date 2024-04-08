package nextstep.courses.domain.session;

import nextstep.courses.exception.ExceedSessionCapacityException;
import nextstep.courses.exception.SessionException;

public class SessionCapacityCondition implements SessionEnrollmentCondition {

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

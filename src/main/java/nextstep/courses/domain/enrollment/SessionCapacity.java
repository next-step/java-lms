package nextstep.courses.domain.enrollment;

import nextstep.courses.exception.SessionCapacityExceedException;

public class SessionCapacity {

    public static final int MIN_CAPACITY = 0;

    private final Long id;
    private final Long sessionId;
    private final int capacity;

    public SessionCapacity(Long id, Long sessionId, int capacity) {
        validateCapacity(capacity);
        this.id = id;
        this.sessionId = sessionId;
        this.capacity = capacity;
    }

    private void validateCapacity(int capacity) {
        if (!isGreaterThanMinCapacity(capacity)) {
            throw new SessionCapacityExceedException(capacity);
        }
    }

    private boolean isGreaterThanMinCapacity(int capacity) {
        return capacity > MIN_CAPACITY;
    }

    public boolean hasCapacity(Students students) {
        return capacity > students.size();
    }

}

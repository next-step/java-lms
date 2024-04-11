package nextstep.courses.domain.enrollment;

import nextstep.courses.exception.SessionCapacityExceedException;

public class SessionCapacity {

    public static final int MIN_CAPACITY = 0;
    public static final int INFINITY = Integer.MAX_VALUE;

    private final Long id;
    private final Long sessionId;
    private final int capacity;

    public SessionCapacity(Long id, Long sessionId, int capacity) {
        validate(capacity);
        this.id = id;
        this.sessionId = sessionId;
        this.capacity = capacity;
    }

    private void validate(int capacity) {
        if (capacity < MIN_CAPACITY) {
            throw new SessionCapacityExceedException(capacity);
        }
    }

    public boolean noCapacity(int currentSize) {
        return capacity <= currentSize;
    }

    public int get() {
        return capacity;
    }

}

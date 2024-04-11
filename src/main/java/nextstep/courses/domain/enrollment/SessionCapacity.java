package nextstep.courses.domain.enrollment;

import nextstep.courses.exception.SessionCapacityExceedException;

public class SessionCapacity {

    public static final int MIN_CAPACITY = 0;

    private final Long id;
    private final Long sessionId;
    private final int capacity;
    private final Students students;

    public SessionCapacity(Long id, Long sessionId, int capacity) {
        validate(capacity);
        this.id = id;
        this.sessionId = sessionId;
        this.capacity = capacity;
        this.students = new Students(sessionId);
    }

    private void validate(int capacity) {
        if (capacity < MIN_CAPACITY) {
            throw new SessionCapacityExceedException(capacity);
        }
    }

    public void validateRemainingCapacity() {
        if (capacity <= students.size()) {
            throw new SessionCapacityExceedException(capacity, students.size());
        }
        students.size();
    }

}

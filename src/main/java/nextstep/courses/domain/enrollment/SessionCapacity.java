package nextstep.courses.domain.enrollment;

import nextstep.courses.exception.SessionCapacityExceedException;

public class SessionCapacity {

    public static final int MIN_CAPACITY = 0;
    public static final int INFINITY = Integer.MAX_VALUE;

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

    public void hasCapacity() {
        if (capacity <= students.size()) {
            throw new SessionCapacityExceedException(this);
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public int currentCapacity() {
        return students.size();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

}

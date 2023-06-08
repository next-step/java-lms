package nextstep.courses.domain.enrollment;

import nextstep.courses.domain.SessionStatus;
import nextstep.courses.domain.SessionType;
import nextstep.courses.exception.CannotRegisterException;

public class Enrollment {
    private final SessionStatus status;
    private final SessionType type;
    private final Students students;

    public Enrollment(SessionStatus status, SessionType type, int capacity) {
        this(status, type, new Students(capacity));
    }

    public Enrollment(SessionStatus status, SessionType type, Students students) {
        this.status = status;
        this.type = type;
        this.students = students;
    }

    public void enroll(Student student) {
        if (!status.isRecruiting()) {
            throw new CannotRegisterException("현재 모집중인 강의가 아닙니다");
        }
        students.add(student);
    }

    public SessionStatus getStatus() {
        return status;
    }

    public SessionType getType() {
        return type;
    }

    public int getCapacity() {
        return students.capacity();
    }

    public Students getStudents() {
        return students;
    }
}

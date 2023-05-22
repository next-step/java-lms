package nextstep.courses.domain;

import java.util.Objects;

public class Enrollment {
    private final int capacity;

    private final SessionStatus sessionStatus;

    private final Students students = new Students();

    public Enrollment(int capacity, SessionStatus sessionStatus) {
        this.capacity = capacity;
        this.sessionStatus = sessionStatus;
    }

    public void enroll(Student student) throws AlreadyEnrollmentException {
        if (sessionStatus != SessionStatus.ENROLLING) {
            throw new IllegalArgumentException("수강신청 상태가 아니라 수강신청할 수 없습니다.");
        }
        if (students.isFullCapacity(capacity)) {
            throw new IllegalArgumentException("최대 수용 인원인 " + capacity + "명을 초과할 수 없습니다.");
        }
        students.enroll(student);
    }

    public void enroll(int sizeOfStudents) {
        if (sessionStatus != SessionStatus.ENROLLING) {
            throw new IllegalArgumentException("수강신청 상태가 아니라 수강신청할 수 없습니다.");
        }
        if (sizeOfStudents >= capacity) {
            throw new IllegalArgumentException("최대 수용 인원인 " + capacity + "명을 초과할 수 없습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enrollment that = (Enrollment) o;
        return capacity == that.capacity && sessionStatus == that.sessionStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(capacity, sessionStatus);
    }
}

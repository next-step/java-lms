package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.Objects;

public class Enrollment {
    private final int capacity;

    private final SessionStatus sessionStatus;

    private final Students students;

    public Enrollment(int capacity, SessionStatus sessionStatus) {
        this.capacity = capacity;
        this.sessionStatus = sessionStatus;
        this.students = new Students(capacity);
    }

    public void enroll(NsUser student) throws AlreadyEnrollmentException {
        if (!sessionStatus.isEnrolling()) {
            throw new IllegalArgumentException("수강신청 상태가 아니라 수강신청할 수 없습니다.");
        }
        students.enroll(student);
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

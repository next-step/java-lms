package nextstep.courses.domain.enrollment;


import nextstep.courses.domain.student.Students;
import nextstep.courses.enums.SessionStatus;
import nextstep.courses.exceptions.AlreadyEnrollmentException;
import nextstep.users.domain.NsUser;

import java.util.Objects;

public class Enrollment {

    private final SessionStatus sessionStatus;

    private final int capacity;

    private final Students students;

    public Enrollment(SessionStatus sessionStatus, int capacity, final Students students) {
        this.sessionStatus = sessionStatus;
        this.capacity = capacity;
        this.students = students;
    }

    public void enroll(NsUser student) throws AlreadyEnrollmentException {
        if (sessionStatus.isEnrolling() == false) {
            throw new IllegalArgumentException("종료된 세션에는 수강 신청할 수 없습니다.");
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

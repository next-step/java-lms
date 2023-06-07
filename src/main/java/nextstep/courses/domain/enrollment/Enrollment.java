package nextstep.courses.domain.enrollment;


import nextstep.courses.domain.student.Students;
import nextstep.courses.enums.SessionStatus;
import nextstep.courses.exceptions.AlreadyEnrollmentException;
import nextstep.users.domain.NsUser;

import java.util.List;
import java.util.Objects;

public class Enrollment {

    private final SessionStatus sessionStatus;

    private final int capacity;

    public Enrollment(SessionStatus sessionStatus, int capacity) {
        this.sessionStatus = sessionStatus;
        this.capacity = capacity;
    }

    public void enroll(NsUser student, List<NsUser> value) throws AlreadyEnrollmentException {
        if (sessionStatus.isEnrolling() == false) {
            throw new IllegalArgumentException("종료된 세션에는 수강 신청할 수 없습니다.");
        }
        final Students students = new Students(this.capacity, value);
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

package nextstep.courses.domain;

import nextstep.courses.CannotApproveException;
import nextstep.courses.TeacherNotMatchException;
import nextstep.courses.domain.session.Session;
import nextstep.users.domain.NsUser;

import java.util.Objects;

public class Student {
    private final long sessionId;
    private final long nsUserId;
    private EnrollmentStatus enrollmentStatus;

    public Student(long sessionId, long nsUserId) {
        this(sessionId, nsUserId, EnrollmentStatus.WAITING);
    }

    public Student(long sessionId, long nsUserId, String enrollmentStatus) {
        this(sessionId, nsUserId, EnrollmentStatus.valueOf(enrollmentStatus));
    }

    public Student(long sessionId, long nsUserId, EnrollmentStatus enrollmentStatus) {
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.enrollmentStatus = enrollmentStatus;
    }

    public void updateEnrollmentStatus(Session session, NsUser teacher, EnrollmentStatus updatedEnrollmentStatus) throws TeacherNotMatchException, CannotApproveException {
        session.matchTeacher(teacher);
        if (updatedEnrollmentStatus.isApproved()) {
            session.canApprove();
            session.addUserNumber();
        }
        this.enrollmentStatus = updatedEnrollmentStatus;
    }

    public Long sessionId() {
        return sessionId;
    }

    public Long nsUserId() {
        return nsUserId;
    }

    public EnrollmentStatus enrollmentStatus() {
        return enrollmentStatus;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sessionId=" + sessionId +
                ", nsUserId=" + nsUserId +
                ", enrollmentStatus=" + enrollmentStatus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student that = (Student) o;
        return sessionId == that.sessionId && nsUserId == that.nsUserId && enrollmentStatus == that.enrollmentStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId, nsUserId, enrollmentStatus);
    }

}

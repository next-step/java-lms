package nextstep.courses.domain.session;

import java.util.Objects;

public class Student {

    private long nsUserId;
    private long sessionId;
    private EnrollmentStatus enrollmentStatus;

    private Student(long nsUserId, long sessionId) {
        this.nsUserId = nsUserId;
        this.sessionId = sessionId;
        this.enrollmentStatus = EnrollmentStatus.PENDING;
    }

    private Student(long nsUserId, long sessionId, EnrollmentStatus enrollmentStatus) {
        this.nsUserId = nsUserId;
        this.sessionId = sessionId;
        this.enrollmentStatus = enrollmentStatus;
    }

    public static Student of(long nsUserId, long sessionId) {
        return new Student(nsUserId, sessionId);
    }

    public static Student of(long nsUserId, long sessionId, EnrollmentStatus enrollmentStatus) {
        return new Student(nsUserId, sessionId, enrollmentStatus);
    }

    public boolean matchStudents(Student student) {
        return matchStudentId(student.getNsUserId()) && matchSessionId(student.getSessionId());
    }

    private boolean matchSessionId(long sessionId) {
        return this.sessionId == sessionId;
    }

    private boolean matchStudentId(long nsUserId) {
        return this.nsUserId == nsUserId;
    }

    public boolean isApproved() {
        return enrollmentStatus.isApproved();
    }

    public boolean isRejected() {
        return enrollmentStatus.isRejected();
    }

    public EnrollmentStatus getEnrollmentStatus() {
        return enrollmentStatus;
    }

    public void approve() {
        enrollmentStatus = EnrollmentStatus.APPROVED;
    }

    public void reject() {
        enrollmentStatus = EnrollmentStatus.REJECTED;
    }

    public long getNsUserId() {
        return nsUserId;
    }

    public long getSessionId() {
        return sessionId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return nsUserId == student.nsUserId && sessionId == student.sessionId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nsUserId, sessionId, enrollmentStatus);
    }
}

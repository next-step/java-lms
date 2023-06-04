package nextstep.users.domain;

import nextstep.courses.domain.registration.EnrollmentStatus;

import java.util.Objects;

public class Student {
    private Long id;
    private Long nsUserId;
    private Long sessionId;
    private EnrollmentStatus enrollmentStatus;

    public Student(Long nsUserId, Long sessionId) {
        this(0L, nsUserId, sessionId, EnrollmentStatus.PENDING);
    }

    public Student(Long nsUserId, Long sessionId, EnrollmentStatus enrollmentStatus) {
        this(0L, nsUserId, sessionId, enrollmentStatus);
    }

    public Student(Long id, Long nsUserId, Long sessionId, EnrollmentStatus enrollmentStatus) {
        this.id = id;
        this.nsUserId = nsUserId;
        this.sessionId = sessionId;
        this.enrollmentStatus = enrollmentStatus;
    }

    public Long getNsUserId() {
        return nsUserId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public EnrollmentStatus getEnrollmentStatus() {
        return enrollmentStatus;
    }

    public Student approveSession() {
        enrollmentStatus = EnrollmentStatus.APPROVED;
        return this;
    }

    public boolean isSessionPending() {
        return enrollmentStatus.isPending();
    }

    public String getSessionRegistrationStatus() {
        return enrollmentStatus.getDescription();
    }

    public boolean isEqualSession(Long otherSessionId) {
        return sessionId == otherSessionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(nsUserId, student.nsUserId) && Objects.equals(sessionId, student.sessionId) && enrollmentStatus == student.enrollmentStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nsUserId, sessionId, enrollmentStatus);
    }
}

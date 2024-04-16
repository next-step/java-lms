package nextstep.courses.domain.student;

import nextstep.courses.infrastructure.entity.BaseEntity;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

import static nextstep.courses.domain.student.StudentEnrollmentStatus.*;

public class SessionStudent extends BaseEntity {

    private Long id;
    private Long sessionId;
    private Long nsUserId;
    private StudentEnrollmentStatus enrollmentStatus;

    public SessionStudent(Long id, Long sessionId, Long nsUserId, String enrollmentString, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.enrollmentStatus = convert(enrollmentString);
    }

    public SessionStudent(Long sessionId, Long nsUserId) {
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.enrollmentStatus = PENDING;
    }

    public static SessionStudent from(Long sessionId, NsUser nsUser) {
        return new SessionStudent(sessionId, nsUser.getId());
    }

    public boolean sameAs(SessionStudent student) {
        return equals(student);
    }

    public void toApproveStatus() {
        this.enrollmentStatus = APPROVAL;
    }

    public void toCancelStatus() {
        this.enrollmentStatus = CANCEL;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public Long getNsUserId() {
        return nsUserId;
    }

    public StudentEnrollmentStatus getEnrollmentStatus() {
        return enrollmentStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionStudent student = (SessionStudent) o;
        return Objects.equals(id, student.id) && Objects.equals(sessionId, student.sessionId) && Objects.equals(nsUserId, student.nsUserId) && enrollmentStatus == student.enrollmentStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sessionId, nsUserId, enrollmentStatus);
    }
}

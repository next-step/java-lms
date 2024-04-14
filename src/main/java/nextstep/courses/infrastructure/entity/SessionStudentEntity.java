package nextstep.courses.infrastructure.entity;

import nextstep.courses.domain.enrollment.SessionStudent;

import java.time.LocalDateTime;

public class SessionStudentEntity extends BaseEntity {

    private long id;
    private final long sessionId;
    private final long nsUserId;

    public SessionStudentEntity(long id, long sessionId, long nsUserId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
    }

    public SessionStudentEntity(SessionStudent student, LocalDateTime createdAt) {
        super(createdAt);
        this.sessionId = student.getSessionId();
        this.nsUserId = student.getNsUserId();
    }

    public long getSessionId() {
        return sessionId;
    }

    public long getNsUserId() {
        return nsUserId;
    }
}

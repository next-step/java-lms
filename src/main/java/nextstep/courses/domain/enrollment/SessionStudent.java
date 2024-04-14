package nextstep.courses.domain.enrollment;

import nextstep.courses.infrastructure.entity.BaseEntity;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class SessionStudent extends BaseEntity {

    private Long id;
    private Long sessionId;
    private Long nsUserId;

    public static SessionStudent from(Long sessionId, NsUser nsUser) {
        return new SessionStudent(sessionId, nsUser.getId());
    }

    public SessionStudent(Long id, Long sessionId, Long nsUserId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
    }

    public SessionStudent(Long sessionId, Long nsUserId) {
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public Long getNsUserId() {
        return nsUserId;
    }
}

package nextstep.courses.domain.enrollment;

import java.time.LocalDateTime;
import nextstep.core.domain.BaseEntity;

public class Enrollment extends BaseEntity {
    private final Long sessionId;
    private final Long userId;

    public Enrollment(Long sessionId, Long userId) {
        this(null, sessionId, userId, LocalDateTime.now(), null);
    }

    public Enrollment(Long id, Long sessionId, Long userId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
        this.sessionId = sessionId;
        this.userId = userId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public Long getUserId() {
        return userId;
    }
}

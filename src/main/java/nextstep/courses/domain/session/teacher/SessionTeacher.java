package nextstep.courses.domain.session.teacher;

import java.time.LocalDateTime;

public class SessionTeacher {
    private Long id;
    private Long sessionId;
    private Long nsUserId;
    private boolean isActive;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public SessionTeacher(Long sessionId, Long nsUserId) {
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
    }

    public SessionTeacher (
        Long id, Long sessionId, Long nsUserId, boolean isActive,
        LocalDateTime createAt, LocalDateTime updateAt
    ) {
        this.id = id;
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.isActive = isActive;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public Long getNsUserId() {
        return nsUserId;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean isNotActive() {
        return !isActive;
    }
}

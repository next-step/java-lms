package nextstep.courses.domain;

import nextstep.common.domain.BaseControlField;

import java.time.LocalDateTime;

public class Student extends BaseControlField {
    private int sessionId;
    private Long userId;

    public Student(int sessionId, Long userId) {
        this(sessionId, userId, userId, LocalDateTime.now(), null);
    }

    public Student(int sessionId, Long userId, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(creatorId, createdAt, updatedAt);
        this.sessionId = sessionId;
        this.userId = userId;
    }

    public int getSessionId() {
        return sessionId;
    }

    public Long getUserId() {
        return userId;
    }
}

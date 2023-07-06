package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Student {
    private long id;
    private final long sessionId;
    private final long userId;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public Student(long sessionId, long userId) {
        this(1L, sessionId, userId, LocalDateTime.now(), null);
    }

    public Student(long id, long sessionId, long userId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.sessionId = sessionId;
        this.userId = userId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public long getId() {
        return id;
    }

    public long getSessionId() {
        return sessionId;
    }

    public long getUserId() {
        return userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}

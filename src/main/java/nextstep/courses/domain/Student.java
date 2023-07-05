package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Student {
    private long id;
    private final long session_id;
    private final long user_id;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public Student(long session_id, long user_id) {
        this(1L, session_id, user_id, LocalDateTime.now(), null);
    }

    public Student(long id, long session_id, long user_id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.session_id = session_id;
        this.user_id = user_id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public long getId() {
        return id;
    }

    public long getSession_id() {
        return session_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}

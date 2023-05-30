package nextstep.courses.domain.student;

import java.time.LocalDateTime;
import java.util.Objects;

public class Student {

    private Long id;

    private String userId;

    private Long sessionId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Student(String userId, Long sessionId) {
        this(null, userId, sessionId, LocalDateTime.now(), null);
    }

    public Student(Long id, String userId, Long sessionId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.sessionId = sessionId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Student() {
    }

    public boolean equalUserId(String userId) {
        return this.userId.equals(userId);
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student that = (Student) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", userId=" + userId +
                ", sessionId=" + sessionId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

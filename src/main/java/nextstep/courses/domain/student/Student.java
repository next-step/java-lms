package nextstep.courses.domain.student;

import nextstep.courses.domain.session.Session;

import java.time.LocalDateTime;
import java.util.Objects;

public class Student {

    private Long id;

    private String userId;

    private Session session;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Student(String userId, Session session) {
        this(null, userId, session, LocalDateTime.now(), null);
    }

    public Student(Long id, String userId, Session session, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.session = session;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Student() {
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public Session getSession() {
        return session;
    }

    public Long getSessionId() {
        return session.getId();
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
                ", session=" + session +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

}

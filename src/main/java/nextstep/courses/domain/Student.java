package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

public class Student {
    private final long id;
    private final long sessionId;
    private final long nsUserId;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Student(Session session, NsUser nsUser, LocalDateTime createdAt) {
        this(0L, session.getId(), nsUser.getId(), createdAt,null);
    }

    public Student(long id, long sessionId, long nsUserId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Object[] toParameters() {
        return new Object[]{
                sessionId,
                nsUserId,
                createdAt
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && sessionId == student.sessionId && nsUserId == student.nsUserId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sessionId, nsUserId);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", sessionId=" + sessionId +
                ", nsUserId=" + nsUserId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

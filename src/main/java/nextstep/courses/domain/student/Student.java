package nextstep.courses.domain.student;

import nextstep.courses.domain.session.Session;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

public class Student {

    private Long id;

    private NsUser nsUser;

    private Session session;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Student(NsUser nsUser, Session session) {
        this(null, nsUser, session, LocalDateTime.now(), null);
    }

    public Student(Long id, NsUser nsUser, Session session, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.nsUser = nsUser;
        this.session = session;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Student() {
    }

    public Long getId() {
        return id;
    }

    public NsUser getNsUser() {
        return nsUser;
    }

    public String getNsUserId() {
        return nsUser.getUserId();
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
                ", nsUser=" + nsUser +
                ", session=" + session +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

}

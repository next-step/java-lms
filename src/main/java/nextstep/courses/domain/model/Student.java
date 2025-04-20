package nextstep.courses.domain.model;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

public class Student {
    private final Long id;
    private final NsUser user;
    private final Session session;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public Student(NsUser user, Session session) {
        this(null, user, session, LocalDateTime.now(), LocalDateTime.now());
    }

    public Student(Long id, NsUser user, Session session, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.user = user;
        this.session = session;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void pay(Long price) {
        user.pay(price);
    }

    public NsUser getNsUser() {
        return user;
    }

    public Session getSession() {
        return session;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(user, student.user) && Objects.equals(session, student.session);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, session);
    }

    @Override
    public String toString() {
        return "Student{" +
                "user=" + user +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

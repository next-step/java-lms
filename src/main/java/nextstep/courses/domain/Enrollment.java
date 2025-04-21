package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Enrollment {
    private Long id;
    private final Session session;
    private final Student student;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Enrollment(Long id, Session session, Student student, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.session = session;
        this.student = student;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Enrollment(Session session, Student student) {
        this.session = session;
        this.student = student;
    }

    public Session getSession() {
        return session;
    }

    public Student getStudent() {
        return student;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Enrollment that = (Enrollment) object;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

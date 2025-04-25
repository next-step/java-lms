package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Enrollment {
    private Long id;
    private final Session session;
    private final Student student;
    private EnrollmentStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Enrollment(Long id, Session session, Student student, EnrollmentStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.session = session;
        this.student = student;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Enrollment request(Session session, Student student) {
        return new Enrollment(null, session, student, EnrollmentStatus.REQUESTED, LocalDateTime.now(), LocalDateTime.now());
    }

    public void approve() {
        updateStatus(status.approve());
    }

    public void reject() {
        updateStatus(status.reject());
    }

    public Long getId() {
        return id;
    }

    public Session getSession() {
        return session;
    }

    public Student getStudent() {
        return student;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public void updateStatus(EnrollmentStatus status) {
        this.status = status;
    }

    public void assignId(long id) {
        this.id = id;
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

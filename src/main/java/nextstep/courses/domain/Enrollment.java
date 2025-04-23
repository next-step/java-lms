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
        if (status == EnrollmentStatus.REJECTED) {
            throw new IllegalStateException("이미 반려된 신청 건 입니다.");
        }

        status = EnrollmentStatus.APPROVED;
    }

    public void reject() {
        if (status == EnrollmentStatus.APPROVED) {
            throw new IllegalStateException("이미 승인된 신청 건 입니다.");
        }

        status = EnrollmentStatus.REJECTED;
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

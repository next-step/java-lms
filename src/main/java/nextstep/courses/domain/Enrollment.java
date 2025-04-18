package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Enrollment {
    private Long id;
    private Session session;
    private Student student;
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

}

package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Enrollment {
    private Long id;
    private Session session;
    private Student student;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Enrollment(Session session, Student student) {
        this.session = session;
        this.student = student;
    }

}

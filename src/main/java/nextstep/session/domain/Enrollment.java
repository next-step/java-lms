package nextstep.session.domain;

import nextstep.common.domain.BaseDomain;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Enrollment extends BaseDomain {
    private NsUser student;
    private Long studentId;
    private Long sessionId;

    public Enrollment(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, Long studentId, Long sessionId) {
        super(id, createdAt, updatedAt);
        this.studentId = studentId;
        this.sessionId = sessionId;
    }

    public Enrollment(NsUser student) {
        this.student = student;
        this.studentId = student.getId();
    }

    public Enrollment(NsUser student, Session session) {
        this.student = student;
        this.studentId = student.getId();
        this.sessionId = session.getId();
    }

    public Long getStudentId() {
        return studentId;
    }

    public Long getSessionId() {
        return sessionId;
    }
}

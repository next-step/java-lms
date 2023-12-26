package nextstep.session.domain;

import nextstep.common.domain.BaseDomain;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Enrollment extends BaseDomain {
    private Long id;
    private Long studentId;
    private Long sessionId;
    private boolean approved;

    public Enrollment(NsUser student, Session session) {
        this(null, null, null, student.getId(), session.getId(), false);
    }

    public Enrollment(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, Long studentId, Long sessionId, boolean approved) {
        super(createdAt, updatedAt);
        this.id = id;
        this.studentId = studentId;
        this.sessionId = sessionId;
        this.approved = approved;
    }

    public void admiss() {
        this.approved = true;
    }

    public Long getId() {
        return id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public boolean isApproved() {
        return approved;
    }
}

package nextstep.courses.infrastructure.entity;

import nextstep.courses.domain.model.Session;
import nextstep.courses.domain.model.Student;
import nextstep.users.domain.NsUser;

import java.sql.Timestamp;

public class JdbcStudent extends BaseEntity {
    private Long sessionId;
    private Long nsUserId;

    public JdbcStudent() {
        super();
    }

    public JdbcStudent(Long id, Long sessionId, Long nsUserId, Timestamp createdAt, Timestamp updatedAt) {
        super(id, createdAt, updatedAt);
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public Long getNsUserId() {
        return nsUserId;
    }

    public void setNsUserId(Long nsUserId) {
        this.nsUserId = nsUserId;
    }

    public Student toDomain(NsUser nsUser, Session session) {
        return new Student(getId(), nsUser, session, getCreatedAt(), getUpdatedAt());
    }
}

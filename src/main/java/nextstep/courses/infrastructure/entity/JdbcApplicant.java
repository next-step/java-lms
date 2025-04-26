package nextstep.courses.infrastructure.entity;

import nextstep.courses.domain.model.Applicant;
import nextstep.courses.domain.model.ApplicantStatus;
import nextstep.users.domain.NsUser;

import java.sql.Timestamp;

public class JdbcApplicant extends BaseEntity {
    private Long sessionId;
    private Long nsUserId;

    public JdbcApplicant() {
        super();
    }

    public JdbcApplicant(Long id, Long sessionId, Long nsUserId, Timestamp createdAt, Timestamp updatedAt) {
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

    public Applicant toDomain(NsUser nsUser) {
        return new Applicant(getId(), sessionId, nsUser, null, ApplicantStatus.APPROVED,
                getCreatedAt().toLocalDateTime(),
                getUpdatedAt() == null ? null : getUpdatedAt().toLocalDateTime());
    }
}

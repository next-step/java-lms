package nextstep.courses.domain.enrollment;

import nextstep.courses.domain.BaseEntity;

import java.time.LocalDateTime;

public class Enrollment extends BaseEntity {
    private Long nsUserId;
    private Long sessionId;
    private boolean approved;

    public Enrollment(final Long nsUserId, final Long sessionId) {
        this(null, nsUserId, sessionId, false, LocalDateTime.now(), null);
    }

    public Enrollment(final Long id, final Long nsUserId, final Long sessionId, final boolean approved,
                      LocalDateTime createAt, LocalDateTime updatedAt) {
        super(id, createAt, updatedAt);
        this.nsUserId = nsUserId;
        this.sessionId = sessionId;
        this.approved = approved;
    }

    public Long nsUserId() {
        return nsUserId;
    }

    public Long sessionId() {
        return sessionId;
    }

    public boolean getApproved() {
        return approved;
    }

    public void approve() {
        approved = true;
    }

    public void cancel() {
        approved = false;
    }
}

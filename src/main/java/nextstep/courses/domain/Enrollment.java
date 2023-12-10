package nextstep.courses.domain;

import nextstep.common.domain.BaseEntity;

import java.time.LocalDateTime;

public class Enrollment extends BaseEntity {
    private Long nsUserId;
    private Long sessionId;


    public Enrollment(final Long nsUserId, final Long sessionId) {
        this(null, nsUserId, sessionId, LocalDateTime.now(), null);
    }

    public Enrollment(final Long id, final Long nsUserId, final Long sessionId, LocalDateTime createAt, LocalDateTime updatedAt) {
        super(id, createAt, updatedAt);
        this.nsUserId = nsUserId;
        this.sessionId = sessionId;
    }

    public Long nsUserId() {
        return nsUserId;
    }

    public Long sessionId() {
        return sessionId;
    }
}

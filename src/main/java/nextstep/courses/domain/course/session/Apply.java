package nextstep.courses.domain.course.session;

import nextstep.courses.domain.BaseEntity;

import java.time.LocalDateTime;

public class Apply extends BaseEntity {
    private Long sessionId;

    private Long nsUserId;

    public Apply(Long sessionId, Long nsUserId, Long creatorId,
                 LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(creatorId, createdAt, updatedAt);
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public Long getNsUserId() {
        return nsUserId;
    }

    @Override
    public String toString() {
        return "Apply{" +
                "sessionId=" + sessionId +
                ", nsUserId=" + nsUserId +
                '}';
    }
}

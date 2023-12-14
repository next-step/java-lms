package nextstep.courses.domain.course.session;

import nextstep.courses.domain.BaseEntity;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Apply extends BaseEntity {
    private Long sessionId;

    private Long nsUserId;

    private boolean approved;

    public Apply(Session session, NsUser nsUser, boolean approved, LocalDateTime createdAt) {
        this(session.getId(), nsUser.getId(), approved, nsUser.getId(), createdAt, null);
    }

    public Apply(Long sessionId, Long nsUserId, boolean approved, Long creatorId,
                 LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(creatorId, createdAt, updatedAt);
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.approved = approved;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public Long getNsUserId() {
        return nsUserId;
    }

    public boolean isApproved() {
        return approved;
    }

    public Apply setApproved(boolean approved) {
        this.approved = approved;
        return this;
    }

    public void checkApprovePossible() {
        if (this.approved) {
            throw new IllegalArgumentException("이미 수강 승인 상태입니다.");
        }
    }

    public void checkCancelPossible() {
        if (!this.approved) {
            throw new IllegalArgumentException("이미 수강 취소 상태입니다.");
        }
    }

    @Override
    public String toString() {
        return "Apply{" +
                "sessionId=" + sessionId +
                ", nsUserId=" + nsUserId +
                '}';
    }
}

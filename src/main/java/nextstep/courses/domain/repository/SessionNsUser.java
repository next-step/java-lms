package nextstep.courses.domain.repository;

import nextstep.courses.domain.ApprovalStatus;

public class SessionNsUser {

    private final Long id;
    private final Long sessionId;
    private final Long nsUserId;
    private boolean isPicked = false;
    private ApprovalStatus approvalStatus = ApprovalStatus.WAITING;

    public SessionNsUser(Long id, Long sessionId, Long nsUserId) {
        this.id = id;
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
    }

    public Long sessionId() {
        return sessionId;
    }

    public Long nsUserId() {
        return nsUserId;
    }

    public void approve() {
        this.approvalStatus = ApprovalStatus.APPROVED;
    }

    public void reject() {
        this.approvalStatus = ApprovalStatus.REJECTED;
    }
}

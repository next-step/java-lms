package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class SessionUser {
    private final Long id;
    private final Session session;
    private ApprovalStatus approvalStatus;
    private final NsUser nsUser;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public SessionUser(Session session, ApprovalStatus approvalStatus, NsUser nsUser, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(null, session, approvalStatus, nsUser, createdAt, updatedAt);
    }

    public SessionUser(Long id, Session session, ApprovalStatus approvalStatus, NsUser nsUser, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.session = session;
        this.approvalStatus = approvalStatus;
        this.nsUser = nsUser;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public boolean isApproved(){
        return approvalStatus != ApprovalStatus.REJECTION;
    }

    public boolean isIncludeNsUserId(NsUser nsUser) {
        return this.nsUser.getId() == nsUser.getId();
    }

    public long getId() {
        return id;
    }

    public Session getSession() {
        return session;
    }

    public NsUser getNsUser() {
        return nsUser;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public ApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }

    public void approve() {
        this.approvalStatus = ApprovalStatus.APPROVAL;
        this.updatedAt = LocalDateTime.now();
    }

    public void reject() {
        this.approvalStatus = ApprovalStatus.REJECTION;
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "SessionUser{" +
                "id=" + id +
                ", session=" + session +
                ", approvalStatus=" + approvalStatus +
                ", nsUser=" + nsUser +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

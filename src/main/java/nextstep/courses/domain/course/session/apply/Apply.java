package nextstep.courses.domain.course.session.apply;

import nextstep.courses.domain.BaseEntity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Apply extends BaseEntity {
    private final Long sessionId;

    private final Long nsUserId;

    private final ApprovalStatus approvalStatus;

    public Apply(Long sessionId, Long nsUserId, ApprovalStatus approvalStatus, LocalDateTime createdAt) {
        this(sessionId, nsUserId, approvalStatus, nsUserId, createdAt, null);
    }

    public Apply(Long sessionId, Long nsUserId, ApprovalStatus approvalStatus, Long creatorId,
                 LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(creatorId, createdAt, updatedAt);
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.approvalStatus = approvalStatus;
    }

    public boolean isSame(Apply apply) {
        return Objects.equals(this.nsUserId, apply.nsUserId);
    }

    public boolean isSameWithUserId(Long nsUserId) {
        return Objects.equals(this.nsUserId, nsUserId);
    }

    public Long sessionId() {
        return sessionId;
    }

    public Long nsUserId() {
        return nsUserId;
    }

    public ApprovalStatus approval() {
        return approvalStatus;
    }

    public Apply approve(LocalDateTime date) {
        return new Apply(this.sessionId, this.nsUserId, ApprovalStatus.APPROVED,
                this.creatorId(), this.createdAt(), date);
    }

    public Apply cancel(LocalDateTime date) {
        return new Apply(this.sessionId, this.nsUserId, ApprovalStatus.CANCELED,
                this.creatorId(), this.createdAt(), date);
    }

    public boolean notApproved() {
        return !this.approvalStatus.approved();
    }

    public boolean notCanceled() {
        return !this.approvalStatus.canceled();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apply apply = (Apply) o;
        return approvalStatus == apply.approvalStatus && Objects.equals(sessionId, apply.sessionId) && Objects.equals(nsUserId, apply.nsUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId, nsUserId, approvalStatus);
    }

    @Override
    public String toString() {
        return "Apply{" +
                "sessionId=" + sessionId +
                ", nsUserId=" + nsUserId +
                ", approved=" + approvalStatus +
                '}';
    }
}

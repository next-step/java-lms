package nextstep.courses.domain.course.session.apply;

import nextstep.courses.domain.BaseEntity;
import nextstep.courses.domain.course.session.Session;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

public class Apply extends BaseEntity {
    private Long sessionId;

    private Long nsUserId;

    private boolean approved;

    public Apply(Long sessionId, Long nsUserId, boolean approved, LocalDateTime createdAt) {
        this(sessionId, nsUserId, approved, nsUserId, createdAt, null);
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

    public boolean isSame(Apply apply) {
        return Objects.equals(this.nsUserId, apply.nsUserId);
    }

    public boolean isSameWithUserId(Long nsUserId) {
        return Objects.equals(this.nsUserId, nsUserId);
    }

    public boolean isApproved() {
        return approved;
    }

    public boolean isCanceled() {
        return !approved;
    }

    public Apply setApproved(boolean approved) {
        this.approved = approved;
        return this;
    }

    public Apply approve(LocalDateTime date) {
        this.approved = true;
        this.setUpdatedAt(date);

        return this;
    }

    public Apply cancel(LocalDateTime date) {
        this.approved = false;
        this.setUpdatedAt(date);

        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apply apply = (Apply) o;
        return approved == apply.approved && Objects.equals(sessionId, apply.sessionId) && Objects.equals(nsUserId, apply.nsUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId, nsUserId, approved);
    }

    @Override
    public String toString() {
        return "Apply{" +
                "sessionId=" + sessionId +
                ", nsUserId=" + nsUserId +
                ", approved=" + approved +
                '}';
    }
}

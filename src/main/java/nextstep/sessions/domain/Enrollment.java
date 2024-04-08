package nextstep.sessions.domain;

import java.time.LocalDateTime;
import java.util.Objects;

// 강의 신청 정보
public class Enrollment extends BaseEntity {
    private Long id;
    private Long sessionId;
    private Long nsUserId;
    private EnrollmentState state;

    public Enrollment(Long sessionId, Long nsUserId) {
        this(0L, sessionId, nsUserId, EnrollmentState.PENDING, LocalDateTime.now(), null);
    }

    public Enrollment(Long sessionId, Long nsUserId, EnrollmentState state, LocalDateTime createdAt) {
        this(0L, sessionId, nsUserId, state, createdAt, null);
    }

    public Enrollment(Long id, Long sessionId, Long nsUserId, EnrollmentState state, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.state = state == null ? EnrollmentState.PENDING : state;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public boolean disableEnrollment(Long sessionId, Long nsUserId) {
        return this.sessionId.equals(sessionId) && this.nsUserId.equals(nsUserId) && !this.state.isCancelled();
    }

    public Long getId() {
        return id;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public Long getNsUserId() {
        return nsUserId;
    }

    public EnrollmentState getState() {
        return state;
    }

    public void update(Enrollment target) {
        if (!this.id.equals(target.id)) {
            throw new IllegalArgumentException();
        }

        if (!this.sessionId.equals(target.sessionId)) {
            throw new IllegalArgumentException();
        }

        if (!this.nsUserId.equals(target.nsUserId)) {
            throw new IllegalArgumentException();
        }

        this.state = target.state;
        this.updatedAt = target.updatedAt;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Enrollment that = (Enrollment) other;
        return Objects.equals(id, that.id) && Objects.equals(sessionId, that.sessionId) && Objects.equals(nsUserId, that.nsUserId) && state == that.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sessionId, nsUserId, state);
    }
}

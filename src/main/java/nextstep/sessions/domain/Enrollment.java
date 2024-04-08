package nextstep.sessions.domain;

import java.time.LocalDateTime;
import java.util.Objects;

// 신청 정보
public class Enrollment extends BaseEntity {
    private Long sessionId;
    private Long nsUserId;
    private EnrollmentState state;

    public Enrollment(Long sessionId, Long nsUserId) {
        this(sessionId, nsUserId, EnrollmentState.PENDING, LocalDateTime.now(), null);
    }

    public Enrollment(Long sessionId, Long nsUserId, EnrollmentState state, LocalDateTime createdAt) {
        this(sessionId, nsUserId, state, createdAt, null);
    }

    public Enrollment(Long sessionId, Long nsUserId, EnrollmentState state, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.state = state == null ? EnrollmentState.PENDING : state;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public boolean hasEnrollment(Long sessionId, Long nsUserId) {
        return this.sessionId.equals(sessionId) && this.nsUserId.equals(nsUserId) && !this.state.isCancelled();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Enrollment that = (Enrollment) other;
        return Objects.equals(sessionId, that.sessionId) && Objects.equals(nsUserId, that.nsUserId) && state == that.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId, nsUserId, state);
    }
}

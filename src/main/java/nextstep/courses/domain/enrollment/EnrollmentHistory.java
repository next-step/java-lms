package nextstep.courses.domain.enrollment;

import java.time.LocalDateTime;

public class EnrollmentHistory {
    private Long id;
    private final Long sessionId;
    private final Long nsUserId;
    private final EnrollmentStatus status;
    private final Long changedBy;
    private final LocalDateTime changedAt;


    public EnrollmentHistory(Long sessionId, Long nsUserId) {
        this(null, sessionId, nsUserId, EnrollmentStatus.PENDING, null, LocalDateTime.now());
    }
    public EnrollmentHistory(Long sessionId, Long nsUserId, EnrollmentStatus status, Long changedBy) {
        this(null, sessionId, nsUserId, status, changedBy, LocalDateTime.now());
    }

    public EnrollmentHistory(Long id, Long sessionId, Long nsUserId, EnrollmentStatus status, Long changedBy, LocalDateTime changedAt) {
        this.id = id;
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.status = status;
        this.changedBy = changedBy;
        this.changedAt = changedAt;
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

    public EnrollmentStatus getStatus() {
        return status;
    }

    public Long getChangedBy() {
        return changedBy;
    }

    public LocalDateTime getChangedAt() {
        return changedAt;
    }
}

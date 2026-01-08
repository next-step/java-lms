package nextstep.courses.domain.enrollment;

import java.time.LocalDateTime;

public class Enrollment {
    private final Long sessionId;
    private final Long userId;
    private final LocalDateTime enrollmentDate;
    private SelectionStatus status;

    public Enrollment(Long sessionId, Long userId) {
        this(sessionId, userId, SelectionStatus.WAITING, LocalDateTime.now());
    }

    public Enrollment(Long sessionId, Long userId, SelectionStatus status, LocalDateTime enrollmentDate) {
        this.sessionId = sessionId;
        this.userId = userId;
        this.status = status;
        this.enrollmentDate = enrollmentDate;
    }

    public boolean isSameUser(Long userId) {
        return this.userId.equals(userId);
    }

    public Long getSessionId() {
        return sessionId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getStatus() {
        return status.name();
    }

    public void approve() {
        this.status = SelectionStatus.APPROVED;
    }

    public void reject() {
        this.status = SelectionStatus.REJECTED;
    }

    public LocalDateTime getEnrollmentDate() {
        return enrollmentDate;
    }
}

package nextstep.courses.domain.enrollment;

import java.time.LocalDateTime;

public class Enrollment {
    private final Long sessionId;
    private final Long userId;
    private EnrollmentStatus status;
    private final LocalDateTime enrollmentDate;

    public Enrollment(Long sessionId, Long userId) {
        this(sessionId, userId, EnrollmentStatus.WAITING, LocalDateTime.now());
    }

    public Enrollment(Long sessionId, Long userId, EnrollmentStatus status, LocalDateTime enrollmentDate) {
        this.sessionId = sessionId;
        this.userId = userId;
        this.status = status;
        this.enrollmentDate = enrollmentDate;
    }

    public void approve() {
        this.status = EnrollmentStatus.APPROVED;
    }

    public void reject() {
        this.status = EnrollmentStatus.REJECTED;
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

    public LocalDateTime getEnrollmentDate() {
        return enrollmentDate;
    }
}

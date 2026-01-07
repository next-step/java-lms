package nextstep.courses.domain.enrollment;

import java.time.LocalDateTime;

public class Enrollment {
    private final Long sessionId;
    private final Long userId;
    private final LocalDateTime enrollmentDate;

    public Enrollment(Long sessionId, Long userId) {
        this(sessionId, userId, LocalDateTime.now());
    }

    public Enrollment(Long sessionId, Long userId, LocalDateTime enrollmentDate) {
        this.sessionId = sessionId;
        this.userId = userId;
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

    public LocalDateTime getEnrollmentDate() {
        return enrollmentDate;
    }
}

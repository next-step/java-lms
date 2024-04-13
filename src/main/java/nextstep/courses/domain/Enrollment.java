package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

public class Enrollment {

    private static final AtomicLong ID_IDENTITY = new AtomicLong(0);

    private Long id;
    private Long sessionId;
    private Long userId;
    private EnrollmentStatus enrollmentStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Enrollment(Long sessionId, Long userId) {
        this(ID_IDENTITY.getAndIncrement(), sessionId, userId, EnrollmentStatus.PENDING);
    }

    public Enrollment(Long id, Long sessionId, Long userId, EnrollmentStatus enrollmentStatus) {
        this.id = id;
        this.sessionId = sessionId;
        this.userId = userId;
        this.enrollmentStatus = enrollmentStatus;
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    public Enrollment(Long id, Long sessionId, Long userId, EnrollmentStatus enrollmentStatus, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.sessionId = sessionId;
        this.userId = userId;
        this.enrollmentStatus = enrollmentStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void changeEnrollmentStatus(EnrollmentStatus enrollmentStatus) {
        this.enrollmentStatus = enrollmentStatus;
    }

    public boolean isStatus(EnrollmentStatus enrollmentStatus) {
        return this.enrollmentStatus.isSame(enrollmentStatus);
    }

    public Long getId() {
        return id;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public Long getUserId() {
        return userId;
    }

    public EnrollmentStatus getEnrollmentStatus() {
        return enrollmentStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}

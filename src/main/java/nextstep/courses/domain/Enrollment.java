package nextstep.courses.domain;

import java.util.concurrent.atomic.AtomicLong;

public class Enrollment {

    private static final AtomicLong ID_IDENTITY = new AtomicLong(0);

    private Long id;
    private Long userId;
    private Long sessionId;
    private EnrollmentStatus enrollmentStatus = EnrollmentStatus.PENDING;

    public Enrollment(Long userId, Long sessionId) {
        this.id = ID_IDENTITY.getAndIncrement();
        this.userId = userId;
        this.sessionId = sessionId;
    }

    public void changeEnrollmentStatus(EnrollmentStatus enrollmentStatus) {
        this.enrollmentStatus = enrollmentStatus;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public boolean isStatus(EnrollmentStatus enrollmentStatus) {
        return this.enrollmentStatus.isSame(enrollmentStatus);
    }
}

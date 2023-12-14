package nextstep.courses.domain.session;

import nextstep.courses.CannotEnrollException;
import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;

public class Session {
    private Long id;
    private Long courseId;
    private Long generation;
    private SessionPeriod sessionPeriod;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private SessionStatus sessionStatus;
    private SessionCondition sessionCondition;
    private CoverImages coverImages;

    public Session(Long courseId,
                   SessionPeriod sessionPeriod,
                   SessionStatus sessionStatus,
                   SessionCondition sessionCondition) {
        this(0L, courseId, 0L, sessionPeriod, sessionStatus, sessionCondition);
    }

    public Session(Long id,
                   Long courseId,
                   Long generation,
                   SessionPeriod sessionPeriod,
                   SessionStatus sessionStatus,
                   SessionCondition sessionCondition) {
        validate(courseId);
        this.id = id;
        this.courseId = courseId;
        this.generation = generation;
        this.sessionPeriod = sessionPeriod;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = null;
        this.sessionStatus = sessionStatus;
        this.sessionCondition = sessionCondition;
    }

    public Session coverImages(CoverImages coverImages) {
        if (coverImages.hasSameSessionIds(id)) {
            throw new IllegalArgumentException("sessionId가 일치하지 않습니다.");
        }
        this.coverImages = coverImages;
        return this;
    }

    private void validate(Long courseId) {
        if (courseId == null || courseId == 0L) {
            throw new IllegalArgumentException("과정 ID 값은 빈 값이거나 0이 올 수 없습니다.");
        }
    }

    public void enroll(Payment payment, long userNumber) throws CannotEnrollException {
        sessionStatus.canEnroll();
        sessionCondition.match(payment, userNumber);
    }

    public Long courseId() {
        return courseId;
    }

    public Long generation() {
        return generation;
    }

    public CoverImages coverImages() {
        return coverImages;
    }

    public SessionPeriod sessionPeriod() {
        return sessionPeriod;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }

    public SessionStatus sessionStatus() {
        return sessionStatus;
    }

    public SessionCondition sessionCondition() {
        return sessionCondition;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", generation=" + generation +
                ", sessionPeriod=" + sessionPeriod +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", sessionStatus=" + sessionStatus +
                ", sessionCondition=" + sessionCondition +
                ", coverImages=" + coverImages +
                '}';
    }
}

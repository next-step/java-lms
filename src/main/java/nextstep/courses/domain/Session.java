package nextstep.courses.domain;

import nextstep.courses.CannotEnrollException;
import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;

public class Session {
    private Long id;
    private Long courseId;
    private Long generation;
    private CoverImage coverImage;
    private SessionPeriod sessionPeriod;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private SessionStatus sessionStatus;
    private SessionPaymentCondition sessionPaymentCondition;

    public Session(Long courseId,
                   CoverImage coverImage,
                   SessionPeriod sessionPeriod,
                   SessionStatus sessionStatus,
                   SessionPaymentCondition sessionPaymentCondition) {
        this(0L, courseId, 0L, coverImage, sessionPeriod, sessionStatus, sessionPaymentCondition);
    }

    public Session(Long id,
                   Long courseId,
                   Long generation,
                   CoverImage coverImage,
                   SessionPeriod sessionPeriod,
                   SessionStatus sessionStatus,
                   SessionPaymentCondition sessionPaymentCondition) {
        validate(courseId);
        this.id = id;
        this.courseId = courseId;
        this.generation = generation;
        this.coverImage = coverImage;
        this.sessionPeriod = sessionPeriod;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = null;
        this.sessionStatus = sessionStatus;
        this.sessionPaymentCondition = sessionPaymentCondition;
    }

    public Session(Long id,
                   Long courseId,
                   Long generation,
                   CoverImage coverImage,
                   SessionPeriod sessionPeriod,
                   String sessionStatus,
                   SessionPaymentCondition sessionPaymentCondition) {
        this(id, courseId, generation, coverImage, sessionPeriod, SessionStatus.valueOf(sessionStatus), sessionPaymentCondition);
    }

    private void validate(Long courseId) {
        if (courseId == null || courseId == 0L) {
            throw new IllegalArgumentException("과정 ID 값은 빈 값이거나 0이 올 수 없습니다.");
        }
    }

    public void checkStatus() throws CannotEnrollException {
        if (!sessionStatus.isRecruiting()) {
            throw new CannotEnrollException("강의가 모집중인 상태가 아닙니다.");
        }
    }

    public void checkPaidSession(Payment payment, Long userNumber) throws CannotEnrollException {
        sessionPaymentCondition.checkPaidSession(payment, userNumber);
    }

    public Long courseId() {
        return courseId;
    }

    public Long generation() {
        return generation;
    }

    public CoverImage coverImage() {
        return coverImage;
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

    public SessionPaymentCondition sessionPaymentCondition() {
        return sessionPaymentCondition;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", generation=" + generation +
                ", coverImage=" + coverImage +
                ", sessionPeriod=" + sessionPeriod +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", sessionStatus=" + sessionStatus +
                ", sessionPaymentCondition=" + sessionPaymentCondition +
                '}';
    }
}

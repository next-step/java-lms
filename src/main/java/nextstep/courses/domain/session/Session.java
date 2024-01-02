package nextstep.courses.domain.session;

import nextstep.courses.CannotEnrollException;
import nextstep.courses.domain.EnrollmentStatus;
import nextstep.courses.domain.NsUserSession;
import nextstep.courses.domain.NsUserSessions;
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
    private boolean approvalRequired;
    private CoverImages coverImages;
    private NsUserSessions nsUserSessions;

    public Session(Long courseId,
                   SessionPeriod sessionPeriod,
                   SessionStatus sessionStatus,
                   SessionCondition sessionCondition,
                   boolean approvalRequired) {
        this(0L, courseId, 0L, sessionPeriod, sessionStatus, sessionCondition, approvalRequired);
    }

    public Session(Long id,
                   Long courseId,
                   Long generation,
                   SessionPeriod sessionPeriod,
                   SessionStatus sessionStatus,
                   SessionCondition sessionCondition,
                   boolean approvalRequired) {
        validate(courseId);
        this.id = id;
        this.courseId = courseId;
        this.generation = generation;
        this.sessionPeriod = sessionPeriod;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = null;
        this.sessionStatus = sessionStatus;
        this.sessionCondition = sessionCondition;
        this.approvalRequired = approvalRequired;
    }

    public Session with(CoverImages coverImages) {
        if (coverImages.hasSameSessionIds(id)) {
            throw new IllegalArgumentException("sessionId가 일치하지 않습니다.");
        }
        this.coverImages = coverImages;
        return this;
    }

    public Session with(NsUserSessions nsUserSessions) {
        this.nsUserSessions = nsUserSessions;
        return this;
    }

    private void validate(Long courseId) {
        if (courseId == null || courseId == 0L) {
            throw new IllegalArgumentException("과정 ID 값은 빈 값이거나 0이 올 수 없습니다.");
        }
    }

    public NsUserSession enroll(Payment payment) throws CannotEnrollException {
        sessionStatus.canEnroll();
        sessionCondition.match(payment);

        return new NsUserSession(payment.getSessionId(), payment.getNsUserId(), EnrollmentStatus.get(approvalRequired));
    }

    public Long courseId() {
        return courseId;
    }

    public Long generation() {
        return generation;
    }

    public CoverImages with() {
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

    public boolean approvalRequired() {
        return approvalRequired;
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
                ", approvalRequired=" + approvalRequired +
                ", coverImages=" + coverImages +
                ", nsUserSessions=" + nsUserSessions +
                '}';
    }
}

package nextstep.courses.domain.session;

import nextstep.courses.domain.session.constant.SessionStatus;
import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;


public abstract class SessionCore {

    private final SessionRange sessionRange;
    private final SessionPolicy sessionPolicy;
    private final SessionStatus sessionStatus;

    public SessionCore(SessionRange sessionRange, SessionPolicy sessionPolicy, SessionStatus sessionStatus) {
        this.sessionRange = sessionRange;
        this.sessionPolicy = sessionPolicy;
        this.sessionStatus = sessionStatus;
    }

    protected void validatePaymentAmount(Payment payment) {
        if (this.sessionPolicy.isSessionType()) {
            sessionPolicy.matchAmount(payment);
        }
    }

    protected void validateNotFull(Enrollments enrollments) {
        if (this.sessionPolicy.matchSize(enrollments.size())) {
            throw new IllegalArgumentException("수강인원이 초과했습니다.");
        }
    }

    protected void validateSessionStatus() {
        if (!this.sessionStatus.equals(SessionStatus.ACTIVE)) {
            throw new IllegalArgumentException("현재는 강의 모집중이 아닙니다.");
        }
    }

    public LocalDateTime getStartDate() {
        return sessionRange.getStartDate();
    }

    public LocalDateTime getEndDate() {
        return sessionRange.getEndDate();
    }

    public int getMaxCapacity() {
        return sessionPolicy.getMaxCapacity().getValue();
    }

    public Long getTuition() {
        return sessionPolicy.getTuition().getValue();
    }

    public String getSessionType() {
        return sessionPolicy.getSessionType().toString();
    }

    public SessionPolicy getSessionPolicy() {
        return sessionPolicy;
    }

    public SessionRange getSessionRange() {
        return sessionRange;
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }
}

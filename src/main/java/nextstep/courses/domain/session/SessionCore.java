package nextstep.courses.domain.session;

import nextstep.courses.domain.session.constant.SessionStatus;

import java.util.List;

public abstract class SessionCore {

    private final SessionRange sessionRange;
    private final SessionPolicy sessionPolicy;
    private final SessionStatus sessionStatus;

    public SessionCore(SessionRange sessionRange, SessionPolicy sessionPolicy, SessionStatus sessionStatus) {
        this.sessionRange = sessionRange;
        this.sessionPolicy = sessionPolicy;
        this.sessionStatus = sessionStatus;
    }

    protected void validatePaymentAmount(Enrollment enrollment) {
        if (this.sessionPolicy.isSessionType()) {
            enrollment.isPaymentAmount(this.sessionPolicy);
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

    public SessionPolicy getSessionPolicy() {
        return sessionPolicy;
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }
}

package nextstep.courses.domain.session;

import nextstep.courses.domain.session.constant.SessionRecruitmentStatus;
import nextstep.courses.domain.session.constant.SessionStatus;
import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;

public class SessionCore {

    private final SessionRange sessionRange;
    private final SessionPolicy sessionPolicy;
    private final SessionStatus sessionStatus;
    private final SessionRecruitmentStatus sessionRecruitmentStatus;

    public SessionCore(SessionRange sessionRange, SessionPolicy sessionPolicy, SessionStatus sessionStatus, SessionRecruitmentStatus sessionRecruitmentStatus) {
        this.sessionRange = sessionRange;
        this.sessionPolicy = sessionPolicy;
        this.sessionStatus = sessionStatus;
        this.sessionRecruitmentStatus = sessionRecruitmentStatus;
    }

    public void validatePaymentAmount(Payment payment) {
        if (this.sessionPolicy.isSessionType()) {
            sessionPolicy.matchAmount(payment);
        }
    }

    public void validateNotFull(Enrollments enrollments) {
        if (this.sessionPolicy.matchSize(enrollments.size())) {
            throw new IllegalArgumentException("수강인원이 초과했습니다.");
        }
    }

    public void validateSessionStatus() {
        if (this.sessionStatus.equals(SessionStatus.FINISHED)) {
            throw new IllegalArgumentException("종료된 강의입니다.");
        }
    }

    public void validateRecruitmentStatus() {
        if(this.sessionRecruitmentStatus.equals(SessionRecruitmentStatus.NOT_RECRUITING)) {
            throw new IllegalArgumentException("현재는 모집기간이 아닙니다.");
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

    public SessionRecruitmentStatus getSessionRecruitmentStatus() {
        return sessionRecruitmentStatus;
    }
}

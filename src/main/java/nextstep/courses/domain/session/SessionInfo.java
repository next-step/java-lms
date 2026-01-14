package nextstep.courses.domain.session;

import nextstep.courses.domain.capacity.Capacity;
import nextstep.courses.domain.money.Money;
import nextstep.courses.domain.policy.SessionPolicy;

import java.time.LocalDateTime;

public class SessionInfo {
    private final SessionStatus sessionStatus;
    private final SessionPolicy sessionPolicy;
    private final SessionPeriod sessionPeriod;
    private final Capacity enrolledCapacity;

    public SessionInfo(SessionStatus sessionStatus, SessionPolicy sessionPolicy, SessionPeriod sessionPeriod, Capacity capacity) {
        this.sessionStatus = sessionStatus;
        this.sessionPolicy = sessionPolicy;
        this.sessionPeriod = sessionPeriod;
        this.enrolledCapacity = capacity;
    }

    public void validateEnrollment(Money paid, LocalDateTime enrollTime) {
        if (!canEnroll(enrollTime)) {
            throw new IllegalStateException("수강 기간이 아닙니다.");
        }

        sessionPolicy.validate(paid, enrolledCapacity);
    }

    private boolean isOpened() {
        return sessionStatus == SessionStatus.OPEN;
    }

    private boolean canEnroll(LocalDateTime enrollTime) {
        return isOpened() && sessionPeriod.canRegister(enrollTime);
    }
}

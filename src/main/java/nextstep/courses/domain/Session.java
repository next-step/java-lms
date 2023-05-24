package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Session {
    private final Long id;
    private final SessionPeriod sessionPeriod;
    private final PaymentType sessionPayment;
    private final SessionStatus sessionStatus;
    private final List<NsUser> nextStepUsers = new ArrayList<>();
    private final int maximumUserCount;

    public Session(Long id, SessionPeriod sessionPeriod, PaymentType sessionPayment, SessionStatus sessionStatus, int maximumUserCount) {
        this.id = id;
        this.sessionPeriod = sessionPeriod;
        this.sessionPayment = sessionPayment;
        this.sessionStatus = sessionStatus;
        this.maximumUserCount = maximumUserCount;
    }

    public void enroll(NsUser nextStepUser) {
        validateStatus();
        validateMaximumUserCount();
        nextStepUsers.add(nextStepUser);
    }

    public void validateStatus() {
        if (!this.sessionStatus.canEnroll()) {
            throw new IllegalArgumentException("모집중인 강의가 아닙니다.");
        }
    }

    public void validateMaximumUserCount() {
        if (nextStepUsers.size() >= maximumUserCount) {
            throw new IllegalArgumentException("강의 최대 수강 인원이 초과되었습니다.");
        }
    }

    public int enrollmentCount() {
        return this.nextStepUsers.size();
    }
}

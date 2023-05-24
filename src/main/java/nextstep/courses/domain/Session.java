package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

public class Session {
    private final Long id;
    private final SessionPeriod sessionPeriod;
    private final PaymentType sessionPayment;
    private final SessionStatus sessionStatus;
    private final NextStepUsers nextStepUsers;

    public Session(Long id, SessionPeriod sessionPeriod, PaymentType sessionPayment, SessionStatus sessionStatus, int maximumUserCount) {
        this.id = id;
        this.sessionPeriod = sessionPeriod;
        this.sessionPayment = sessionPayment;
        this.sessionStatus = sessionStatus;
        this.nextStepUsers = new NextStepUsers(maximumUserCount);
    }

    public void enroll(NsUser nextStepUser) {
        validateStatus();
        nextStepUsers.enroll(nextStepUser);
    }

    public void validateStatus() {
        if (!this.sessionStatus.canEnroll()) {
            throw new IllegalArgumentException("모집중인 강의가 아닙니다.");
        }
    }

    public int enrollmentCount() {
        return this.nextStepUsers.enrollmentCount();
    }
}

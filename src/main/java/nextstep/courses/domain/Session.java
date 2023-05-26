package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

public class Session {
    private static final String RECRUITMENT_STATUS_MESSAGE = "모집중인 강의가 아닙니다.";

    private final Long id;
    private final SessionPeriod sessionPeriod;
    private PaymentType paymentType;
    private SessionStatus sessionStatus;
    private final NextStepUsers nextStepUsers;
    private SessionImageUrl sessionImageUrl;

    public Session(Long id, SessionPeriod sessionPeriod, PaymentType paymentType, SessionStatus sessionStatus, int maximumUserCount, SessionImageUrl sessionImageUrl) {
        this.id = id;
        this.sessionPeriod = sessionPeriod;
        this.paymentType = paymentType;
        this.sessionStatus = sessionStatus;
        this.nextStepUsers = new NextStepUsers(maximumUserCount);
        this.sessionImageUrl = sessionImageUrl;
    }

    public void enroll(NsUser nextStepUser) {
        validateStatus();
        nextStepUsers.enroll(nextStepUser);
    }

    public void validateStatus() {
        if (!this.sessionStatus.canEnroll()) {
            throw new IllegalArgumentException(RECRUITMENT_STATUS_MESSAGE);
        }
    }

    public int enrollmentCount() {
        return this.nextStepUsers.enrollmentCount();
    }

    public Long getId() {
        return id;
    }

    public SessionPeriod getSessionPeriod() {
        return sessionPeriod;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public SessionImageUrl getSessionImageUrl() {
        return sessionImageUrl;
    }
}

package nextstep.courses.domain;

public class Session {
    public static final String RECRUITMENT_STATUS_MESSAGE = "모집중인 강의가 아닙니다.";

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

    public Session(Long id, SessionPeriod sessionPeriod, PaymentType paymentType, SessionStatus sessionStatus, NextStepUsers nextStepUsers, SessionImageUrl sessionImageUrl) {
        this.id = id;
        this.sessionPeriod = sessionPeriod;
        this.paymentType = paymentType;
        this.sessionStatus = sessionStatus;
        this.nextStepUsers = nextStepUsers;
        this.sessionImageUrl = sessionImageUrl;
    }

    public void enroll(SessionUser sessionUser) {
        validateStatus();
        nextStepUsers.enroll(sessionUser);
    }

    public void validateStatus() {
        if (!this.sessionStatus.canEnroll()) {
            throw new IllegalArgumentException(RECRUITMENT_STATUS_MESSAGE);
        }
    }

    public int enrollmentCount() {
        return this.nextStepUsers.enrollmentCount();
    }

    public int getMaximumEnrollmentCount() {
        return nextStepUsers.getMaximumUserCount();
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

    public NextStepUsers getNextStepUsers() {
        return this.nextStepUsers;
    }

    public SessionImageUrl getSessionImageUrl() {
        return sessionImageUrl;
    }

    public static class Builder {
        private Long id;
        private SessionPeriod sessionPeriod;
        private PaymentType paymentType;
        private SessionStatus sessionStatus;
        private NextStepUsers nextStepUsers;
        private SessionImageUrl sessionImageUrl;

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder with(Session session) {
            this.sessionPeriod = session.sessionPeriod;
            this.paymentType = session.paymentType;
            this.sessionStatus = session.sessionStatus;
            this.nextStepUsers = session.nextStepUsers;
            this.sessionImageUrl = session.sessionImageUrl;
            return this;
        }

        public Session build() {
            return new Session(id, sessionPeriod, paymentType, sessionStatus, nextStepUsers, sessionImageUrl);
        }
    }
}

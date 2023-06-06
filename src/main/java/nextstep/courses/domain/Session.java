package nextstep.courses.domain;

import java.util.List;

public class Session {
    private final Long id;
    private final SessionPeriod sessionPeriod;
    private PaymentType paymentType;
    private Enrollment enrollment;
    private SessionImageUrl sessionImageUrl;

    public Session(Long id, SessionPeriod sessionPeriod, PaymentType paymentType, SessionStatus sessionStatus, int maximumUserCount, SessionImageUrl sessionImageUrl) {
        this.id = id;
        this.sessionPeriod = sessionPeriod;
        this.paymentType = paymentType;
        this.enrollment = new Enrollment(maximumUserCount, sessionStatus);
        this.sessionImageUrl = sessionImageUrl;
    }

    public Session(Long id, SessionPeriod sessionPeriod, PaymentType paymentType, SessionStatus sessionStatus, SessionUsers sessionUsers, SessionImageUrl sessionImageUrl) {
        this.id = id;
        this.sessionPeriod = sessionPeriod;
        this.paymentType = paymentType;
        this.enrollment = new Enrollment(sessionUsers, sessionStatus);
        this.sessionImageUrl = sessionImageUrl;
    }

    public Session(Long id, SessionPeriod sessionPeriod, PaymentType paymentType, Enrollment enrollment, SessionImageUrl sessionImageUrl) {
        this.id = id;
        this.sessionPeriod = sessionPeriod;
        this.paymentType = paymentType;
        this.enrollment = enrollment;
        this.sessionImageUrl = sessionImageUrl;
    }

    public void enroll(SessionUser sessionUser) {
        this.enrollment.enroll(sessionUser);
    }

    public int enrollmentCount() {
        return this.enrollment.enrollmentCount();
    }

    public int getMaximumEnrollmentCount() {
        return this.enrollment.maximumEnrollmentCount();
    }

    public void approve(SessionUser sessionUser) {
        this.enrollment.approve(sessionUser);
    }

    public void reject(SessionUser sessionUser) {
        this.enrollment.reject(sessionUser);
    }

    public void addApprovedUser(Long userId) {
        this.enrollment.addApprovedUser(userId);
    }

    public void addApprovedUsers(List<Long> userIds) {
        this.enrollment.addApprovedUsers(userIds);
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

    public String getProgressStatus() {
        return this.enrollment.getProgressStatus();
    }

    public String getRecruitmentStatus() {
        return this.enrollment.getRecruitmentStatus();
    }

    public SessionImageUrl getSessionImageUrl() {
        return sessionImageUrl;
    }

    public static class Builder {
        private Long id;
        private SessionPeriod sessionPeriod;
        private PaymentType paymentType;
        private Enrollment enrollment;
        private SessionImageUrl sessionImageUrl;

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder with(Session session) {
            this.sessionPeriod = session.sessionPeriod;
            this.paymentType = session.paymentType;
            this.enrollment = session.enrollment;
            this.sessionImageUrl = session.sessionImageUrl;
            return this;
        }

        public Session build() {
            return new Session(id, sessionPeriod, paymentType, enrollment, sessionImageUrl);
        }
    }
}

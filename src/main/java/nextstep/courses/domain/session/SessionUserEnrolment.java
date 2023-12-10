package nextstep.courses.domain.session;

import nextstep.courses.type.SessionSubscriptionStatus;

public class SessionUserEnrolment {
    private final Long id;
    private final Long nsUserId;
    private final Long sessionId;
    private final SessionSubscriptionStatus subscriptionStatus;

    public SessionUserEnrolment(Long nsUserId, Long sessionId, SessionSubscriptionStatus subscriptionStatus) {
        this(null, nsUserId, sessionId, subscriptionStatus);
    }

    public SessionUserEnrolment(Long id, Long nsUserId, Long sessionId, SessionSubscriptionStatus subscriptionStatus) {
        this.id = id;
        this.nsUserId = nsUserId;
        this.sessionId = sessionId;
        this.subscriptionStatus = subscriptionStatus;
    }

    public Long id() {
        return id;
    }

    public Long nsUserId() {
        return nsUserId;
    }

    public Long sessionId() {
        return sessionId;
    }

    public SessionSubscriptionStatus subscriptionStatus() {
        return subscriptionStatus;
    }
}

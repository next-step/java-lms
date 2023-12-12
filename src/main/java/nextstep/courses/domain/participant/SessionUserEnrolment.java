package nextstep.courses.domain.participant;

import nextstep.courses.exception.AlreadyAcceptedUserException;
import nextstep.courses.exception.AlreadyRejectedUserException;
import nextstep.courses.type.SessionSubscriptionStatus;

import java.util.Objects;

public class SessionUserEnrolment {
    private final Long nsUserId;
    private final Long sessionId;
    private final SessionSubscriptionStatus subscriptionStatus;

    public SessionUserEnrolment(Long nsUserId, Long sessionId, SessionSubscriptionStatus subscriptionStatus) {
        this.nsUserId = nsUserId;
        this.sessionId = sessionId;
        this.subscriptionStatus = subscriptionStatus;
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

    public SessionUserEnrolment accept() {
        if (subscriptionStatus.isAccept()) {
            throw new AlreadyAcceptedUserException();
        }
        return new SessionUserEnrolment(this.nsUserId, this.sessionId, SessionSubscriptionStatus.ACCEPT);
    }

    public SessionUserEnrolment reject() {
        if (subscriptionStatus.isReject()) {
            throw new AlreadyRejectedUserException();
        }
        return new SessionUserEnrolment(this.nsUserId, this.sessionId, SessionSubscriptionStatus.REJECT);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof SessionUserEnrolment)) return false;
        SessionUserEnrolment sessionUserEnrolment = (SessionUserEnrolment) obj;
        return Objects.equals(nsUserId, sessionUserEnrolment.nsUserId) &&
                Objects.equals(sessionId, sessionUserEnrolment.sessionId) &&
                Objects.equals(subscriptionStatus, sessionUserEnrolment.subscriptionStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nsUserId, sessionId, subscriptionStatus);
    }
}

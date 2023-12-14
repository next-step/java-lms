package nextstep.courses.domain.participant;

import nextstep.courses.exception.AlreadyAcceptedUserException;
import nextstep.courses.exception.AlreadyRejectedUserException;
import nextstep.courses.type.ApprovalStatus;
import nextstep.courses.type.ParticipantSelectionStatus;
import nextstep.qna.UnAuthorizedException;

import java.util.Objects;

import static nextstep.courses.message.ExceptionMessage.*;

public class SessionUserEnrolment {
    private final Long nsUserId;
    private final Long sessionId;
    private final ParticipantSelectionStatus subscriptionStatus;
    private final ApprovalStatus approvalStatus;

    public SessionUserEnrolment(Long nsUserId, Long sessionId, ParticipantSelectionStatus subscriptionStatus, ApprovalStatus approvalStatus) {
        this.nsUserId = nsUserId;
        this.sessionId = sessionId;
        this.subscriptionStatus = subscriptionStatus;
        this.approvalStatus = approvalStatus;
    }

    public SessionUserEnrolment(Long nsUserId, Long sessionId, ParticipantSelectionStatus subscriptionStatus) {
        this(nsUserId, sessionId, subscriptionStatus, ApprovalStatus.NONE);
    }

    public Long nsUserId() {
        return nsUserId;
    }

    public Long sessionId() {
        return sessionId;
    }

    public ParticipantSelectionStatus subscriptionStatus() {
        return subscriptionStatus;
    }

    public ApprovalStatus approvalStatus() {
        return approvalStatus;
    }

    public SessionUserEnrolment accept() {
        if (subscriptionStatus.isAccept()) {
            throw new AlreadyAcceptedUserException();
        }
        return new SessionUserEnrolment(this.nsUserId, this.sessionId, ParticipantSelectionStatus.ACCEPT, ApprovalStatus.NONE);
    }

    public SessionUserEnrolment reject() {
        if (subscriptionStatus.isReject()) {
            throw new AlreadyRejectedUserException();
        }
        return new SessionUserEnrolment(this.nsUserId, this.sessionId, ParticipantSelectionStatus.REJECT, ApprovalStatus.NONE);
    }

    public SessionUserEnrolment approve() {
        if (!subscriptionStatus.isAccept()) {
            throw new UnAuthorizedException(NOT_ACCEPTED_USER_EXCEPTION.getMessage());
        }
        if (approvalStatus.isApproval()) {
            throw new UnAuthorizedException(NOT_APPROVED_USER_EXCEPTION.getMessage());
        }
        return new SessionUserEnrolment(this.nsUserId, this.sessionId, this.subscriptionStatus, ApprovalStatus.APPROVAL);
    }

    public SessionUserEnrolment cancel() {
        if (!subscriptionStatus.isReject()) {
            throw new UnAuthorizedException(NOT_REJECTED_USER_EXCEPTION.getMessage());
        }
        if (approvalStatus.isCancel()) {
            throw new UnAuthorizedException(NOT_CANCELLED_USER_EXCEPTION.getMessage());
        }
        return new SessionUserEnrolment(this.nsUserId, this.sessionId, this.subscriptionStatus, ApprovalStatus.CANCEL);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof SessionUserEnrolment)) return false;
        SessionUserEnrolment sessionUserEnrolment = (SessionUserEnrolment) obj;
        return Objects.equals(nsUserId, sessionUserEnrolment.nsUserId) &&
                Objects.equals(sessionId, sessionUserEnrolment.sessionId) &&
                Objects.equals(subscriptionStatus, sessionUserEnrolment.subscriptionStatus)
                && Objects.equals(approvalStatus, sessionUserEnrolment.approvalStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nsUserId, sessionId, subscriptionStatus, approvalStatus);
    }
}

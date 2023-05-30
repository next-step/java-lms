package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.Objects;

public class SessionUser {
    private final NsUser nextStepUser;
    private SessionUserStatus sessionUserStatus;

    public SessionUser(NsUser nextStepUser) {
        this(nextStepUser, SessionUserStatus.WAIT);
    }

    public SessionUser(NsUser nextStepUser, SessionUserStatus sessionUserStatus) {
        this.nextStepUser = nextStepUser;
        this.sessionUserStatus = sessionUserStatus;
    }

    public void approve() {
        this.sessionUserStatus = SessionUserStatus.APPROVAL;
    }

    public void reject() {
        this.sessionUserStatus = SessionUserStatus.REJECT;
    }

    public boolean isApproved() {
        return this.sessionUserStatus.isApproved();
    }

    public NsUser getNextStepUser() {
        return this.nextStepUser;
    }

    public SessionUserStatus getSessionUserStatus() {
        return this.sessionUserStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionUser that = (SessionUser) o;
        return Objects.equals(nextStepUser, that.nextStepUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nextStepUser);
    }
}

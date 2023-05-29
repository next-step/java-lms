package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

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
}

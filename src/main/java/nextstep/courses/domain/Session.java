package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

public class Session {
    private static final String CANNOT_ENROLL_SESSION = "강의 수강신청은 강의 상태가 모집중일 때만 가능합니다.";
    private final SessionPeriod sessionPeriod;
    private final SessionCoverImage sessionCoverImage;
    private SessionStatus sessionStatus;
    private final SessionPayment sessionPayment;
    private final SessionUser sessionUser;

    public Session(SessionPeriod sessionPeriod, SessionCoverImage sessionCoverImage, SessionStatus sessionStatus, SessionPayment sessionPayment, SessionUser sessionUser) {
        this.sessionPeriod = sessionPeriod;
        this.sessionCoverImage = sessionCoverImage;
        this.sessionStatus = sessionStatus;
        this.sessionPayment = sessionPayment;
        this.sessionUser = sessionUser;
    }

    public int getUserCount() {
        return sessionUser.getUserCount();
    }

    public void changeSessionStatus(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    public void enrollSession(NsUser nsUser) {
        if (!sessionStatus.canEnrollment()) {
            throw new IllegalArgumentException(CANNOT_ENROLL_SESSION);
        }
        sessionUser.addEnroll(nsUser);
    }

}

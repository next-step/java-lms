package nextstep.courses.domain.session;

public class SessionCondition {

    private final SessionStatus sessionStatus;

    private final SessionType sessionType;

    public SessionCondition(SessionStatus sessionStatus, SessionType sessionType) {
        this.sessionStatus = sessionStatus;
        this.sessionType = sessionType;
    }

    public boolean participateAvailable() {
        return sessionStatus.isRecruit();
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public SessionType getSessionType() {
        return sessionType;
    }
}

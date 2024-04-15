package nextstep.courses.domain;

import nextstep.courses.domain.enums.SessionStatus;

public class SessionInfos {
    private final SessionDate sessionDate;
    private SessionStatus sessionStatus;

    public static SessionInfos createWithReadyStatus(SessionDate sessionDate) {
        return new SessionInfos(sessionDate, SessionStatus.READY);
    }

    public static SessionInfos createWithStatus(SessionDate sessionDate, SessionStatus sessionStatus) {
        return new SessionInfos(sessionDate, sessionStatus);
    }

    private SessionInfos(SessionDate sessionDate, SessionStatus sessionStatus) {
        this.sessionDate = sessionDate;
        this.sessionStatus = sessionStatus;
    }

    public SessionDate getSessionDate() {
        return sessionDate;
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public void startRecruit() {
        sessionStatus = SessionStatus.RECRUITING;
    }

    public boolean isStatusNotRecruiting() {
        return sessionStatus != SessionStatus.RECRUITING;
    }
}

package nextstep.courses.domain;

import nextstep.courses.domain.enums.SessionStatus;
import nextstep.courses.domain.enums.SessionType;

public class SessionInfos {
    private final SessionDate sessionDate;
    private SessionStatus sessionStatus;
    private final SessionType sessionType;

    public static SessionInfos createWithReadyStatus(SessionDate sessionDate, SessionType sessionType) {
        return new SessionInfos(sessionDate, SessionStatus.READY, sessionType);
    }

    public static SessionInfos createWithStatus(SessionDate sessionDate, SessionStatus sessionStatus, SessionType sessionType) {
        return new SessionInfos(sessionDate, sessionStatus, sessionType);
    }

    private SessionInfos(SessionDate sessionDate, SessionStatus sessionStatus, SessionType sessionType) {
        this.sessionDate = sessionDate;
        this.sessionStatus = sessionStatus;
        this.sessionType = sessionType;
    }

    public SessionDate getSessionDate() {
        return sessionDate;
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public SessionType getSessionType() {
        return sessionType;
    }

    public void startRecruit() {
        sessionStatus = SessionStatus.RECRUITING;
    }

    public boolean isStatusNotRecruiting() {
        return sessionStatus != SessionStatus.RECRUITING;
    }
}

package nextstep.courses.domain;

import nextstep.courses.domain.enums.SessionStatus;

public class SessionInfos {
    private final SessionDate sessionDate;
    private SessionStatus sessionStatus;
    private boolean isRecruiting;

    public static SessionInfos createWithDefault(SessionDate sessionDate) {
        return new SessionInfos(sessionDate, SessionStatus.READY, false);
    }

    public static SessionInfos createFromData(SessionDate sessionDate, SessionStatus sessionStatus, boolean isRecruiting) {
        return new SessionInfos(sessionDate, sessionStatus, isRecruiting);
    }

    private SessionInfos(SessionDate sessionDate, SessionStatus sessionStatus, boolean isRecruiting) {
        this.sessionDate = sessionDate;
        this.sessionStatus = sessionStatus;
        this.isRecruiting = isRecruiting;
    }

    public SessionDate getSessionDate() {
        return sessionDate;
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public void startRecruit() {
        isRecruiting = true;
    }

    public boolean isStatusNotRecruiting() {
        return !isRecruiting;
    }
}

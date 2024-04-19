package nextstep.courses.domain;

import java.awt.*;

public class SessionInfo {
    private static Long sessionIdSeed = 0L;

    private String sessionName;

    private Long sessionId;

    private SessionPeriod sessionPeriod;

    private Image image;

    public SessionInfo(String sessionName, SessionPeriod sessionPeriod, Image image) {
        sessionId = sessionIdSeed++;
        this.sessionName = sessionName;
        this.sessionPeriod = sessionPeriod;
        this.image = image;
    }

    public long getSessionId() {
        return sessionId;
    }
}

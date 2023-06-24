package nextstep.courses.domain;

public class SessionInformation {

    private final long sessionId;
    private final SessionType sessionType;
    private final long imageId;

    public SessionInformation(long sessionId, SessionType sessionType, long imageId) {
        this.sessionId = sessionId;
        this.sessionType = sessionType;
        this.imageId = imageId;
    }
}

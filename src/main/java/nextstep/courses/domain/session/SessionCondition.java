package nextstep.courses.domain.session;

public class SessionCondition {

    private final SessionStatus sessionStatus;

    private final SessionType sessionType;

    private final SessionCoverImage sessionCoverImage;

    public SessionCondition(SessionStatus sessionStatus, SessionType sessionType, SessionCoverImage sessionCoverImage) {
        this.sessionStatus = sessionStatus;
        this.sessionType = sessionType;
        this.sessionCoverImage = sessionCoverImage;
    }

    public boolean participateAvailable() {
        return sessionStatus.isRecruit();
    }
}

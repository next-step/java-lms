package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

public class Session {

    private final Long id;

    private final Image coverImage;

    private final SessionInformation sessionInformation;

    public Session(Image coverImage, SessionInformation sessionInformation) {
        this(0L, coverImage, sessionInformation);
    }

    public Session(Long id, Image coverImage, SessionInformation sessionInformation) {
        this.id = id;
        this.coverImage = coverImage;
        this.sessionInformation = sessionInformation;
    }

    public boolean isEqualSessionId(Long sessionId) {
        return this.id.equals(sessionId);
    }

    public void enroll(Long userId) {
        sessionInformation.enroll(userId, this.id);
    }
}

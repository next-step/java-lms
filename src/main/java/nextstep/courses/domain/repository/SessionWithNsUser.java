package nextstep.courses.domain.repository;

public class SessionWithNsUser {

    private final Long id;
    private final Long sessionId;
    private final Long nsUserId;

    public SessionWithNsUser(Long id, Long sessionId, Long nsUserId) {
        this.id = id;
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
    }
}

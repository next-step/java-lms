package nextstep.courses.domain.repository;

public class SessionNsUser {

    private final Long id;
    private final Long sessionId;
    private final Long nsUserId;

    public SessionNsUser(Long id, Long sessionId, Long nsUserId) {
        this.id = id;
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
    }
}

package nextstep.courses.domain;

public class SessionCoverImageInfo {
    private final Long id;
    private final Long sessionId;
    private final Long coverImageInfoId;

    public static SessionCoverImageInfo createNewInstance(Long sessionId, Long coverImageInfoId) {
        return new SessionCoverImageInfo(0L, sessionId, coverImageInfoId);
    }

    public static SessionCoverImageInfo createFromData(Long id, Long sessionId, Long coverImageInfoId) {
        return new SessionCoverImageInfo(id, sessionId, coverImageInfoId);
    }

    private SessionCoverImageInfo(Long id, Long sessionId, Long coverImageInfoId) {
        this.id = id;
        this.sessionId = sessionId;
        this.coverImageInfoId = coverImageInfoId;
    }
}

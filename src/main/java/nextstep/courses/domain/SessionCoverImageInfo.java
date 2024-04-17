package nextstep.courses.domain;

public class SessionCoverImageInfo {
    private final Long id;
    private final Session session;
    private final CoverImageInfo coverImageInfo;

    public static SessionCoverImageInfo createNewInstance(Session session, CoverImageInfo coverImageInfo) {
        return new SessionCoverImageInfo(0L, session, coverImageInfo);
    }

    public static SessionCoverImageInfo createFromData(Long id, Session session, CoverImageInfo coverImageInfo) {
        return new SessionCoverImageInfo(id, session, coverImageInfo);
    }

    private SessionCoverImageInfo(Long id, Session session, CoverImageInfo coverImageInfo) {
        this.id = id;
        this.session = session;
        this.coverImageInfo = coverImageInfo;
    }

    public Long getSessionId() {
        return session.getId();
    }

    public Long getCoverImageInfoId() {
        return coverImageInfo.getId();
    }
}

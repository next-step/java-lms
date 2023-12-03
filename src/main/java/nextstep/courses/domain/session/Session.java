package nextstep.courses.domain.session;

import nextstep.courses.domain.image.CoverImage;

public class Session {
    private final Long id;
    private final String title;
    private final SessionPeriod sessionPeriod;
    private final SessionStatus sessionStatus;
    private final CoverImage coverImage;
    private final int genesis;
    private final Sessions sessions;

    public Session(final Long id, final String title, final SessionPeriod sessionPeriod,
                   final SessionStatus sessionStatus, final CoverImage coverImage,
                   final int genesis, final Sessions sessions) {
        this.id = id;
        this.title = title;
        this.sessionPeriod = sessionPeriod;
        this.sessionStatus = sessionStatus;
        this.coverImage = coverImage;
        this.genesis = genesis;
        this.sessions = sessions;
    }
}

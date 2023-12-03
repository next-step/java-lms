package nextstep.courses.domain.session;

import nextstep.courses.domain.image.CoverImage;

public class Session {
    private final Long id;
    private final String title;
    private final SessionPeriod sessionPeriod;
    private final SessionStatus sessionStatus;
    private final CoverImage coverImage;

    public Session(final Long id, final String title, final SessionPeriod sessionPeriod, final SessionStatus sessionStatus, final CoverImage coverImage) {
        this.id = id;
        this.title = title;
        this.sessionPeriod = sessionPeriod;
        this.sessionStatus = sessionStatus;
        this.coverImage = coverImage;
    }
}

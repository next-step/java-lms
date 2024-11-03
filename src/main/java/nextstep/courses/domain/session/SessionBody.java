package nextstep.courses.domain.session;

import nextstep.courses.domain.cover.CoverImage;

public class SessionBody {

    private final String title;
    private final SessionPeriod period;
    private final CoverImage coverImage;

    private SessionBody(String title, SessionPeriod period, CoverImage coverImage) {
        this.title = title;
        this.period = period;
        this.coverImage = coverImage;
    }

    public static SessionBody of(String title, SessionPeriod period, CoverImage coverImage) {
        return new SessionBody(title, period, coverImage);
    }

    public String getTitle() {
        return title;
    }

    public SessionPeriod getPeriod() {
        return period;
    }

    public CoverImage getCoverImage() {
        return coverImage;
    }

}

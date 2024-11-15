package nextstep.courses.domain.session;

import nextstep.courses.domain.cover.CoverImage;

import java.util.Collections;
import java.util.List;

public class SessionBody {

    private final String title;
    private final SessionPeriod period;
    private final List<CoverImage> coverImages;

    private SessionBody(String title, SessionPeriod period, List<CoverImage> coverImages) {
        this.title = title;
        this.period = period;
        this.coverImages = coverImages;
    }

    public static SessionBody of(String title, SessionPeriod period, List<CoverImage> coverImages) {
        return new SessionBody(title, period, coverImages);
    }

    public String getTitle() {
        return title;
    }

    public SessionPeriod getPeriod() {
        return period;
    }

    public List<CoverImage> getCoverImages() {
        return Collections.unmodifiableList(coverImages);
    }

}

package nextstep.courses.domain.session;

import nextstep.courses.domain.cover.CoverImage;

public class SessionBody {

    private long courseId;
    private String title;
    private SessionPeriod period;
    private CoverImage coverImage;


    private SessionBody(long courseId, String title, SessionPeriod period, CoverImage coverImage) {
        this.courseId = courseId;
        this.title = title;
        this.period = period;
        this.coverImage = coverImage;
    }

    public static SessionBody of(long courseId, String title, SessionPeriod period, CoverImage coverImage) {
        return new SessionBody(courseId, title, period, coverImage);
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

    public long getCourseId() {
        return courseId;
    }


}

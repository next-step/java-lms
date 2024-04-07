package nextstep.courses.domain;

public class Session {

    private final CoverImage coverImage;
    private final EnrollmentManager enrollmentManager;
    private final SessionPeriod sessionPeriod;
    public Session(CoverImage coverImage, EnrollmentManager enrollmentManager, SessionPeriod sessionPeriod) {
        this.coverImage = coverImage;
        this.enrollmentManager = enrollmentManager;
        this.sessionPeriod = sessionPeriod;
    }
}

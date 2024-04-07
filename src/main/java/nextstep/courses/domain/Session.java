package nextstep.courses.domain;

public class Session {

    private final CoverImage coverImage;
    private final EnrollmentManager enrollmentManager;
    private final SessionStatus sessionStatus;
    private final SessionPeriod sessionPeriod;
    public Session(CoverImage coverImage, EnrollmentManager enrollmentManager, SessionStatus sessionStatus, SessionPeriod sessionPeriod) {
        this.coverImage = coverImage;
        this.enrollmentManager = enrollmentManager;
        this.sessionStatus = sessionStatus;
        this.sessionPeriod = sessionPeriod;
    }
}

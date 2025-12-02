package nextstep.courses.domain.session.builder;

import nextstep.courses.domain.image.CoverImage;
import nextstep.courses.domain.session.*;
import nextstep.courses.domain.session.constant.SessionStatus;


public class SessionBuilder {

    private Long id = 1L;
    private CoverImage coverImage = new CoverImage(1L, "png", 300, 200);
    private SessionRange sessionRange = new SessionRangeBuilder().build();
    private SessionPolicy sessionPolicy = new SessionPolicyBuilder().build();
    private SessionStatus sessionStatus = SessionStatus.PENDING;
    private Enrollments enrollments = new Enrollments();

    public SessionBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public SessionBuilder withCoverImage(CoverImage coverImage) {
        this.coverImage = coverImage;
        return this;
    }

    public SessionBuilder withSessionRange(SessionRange sessionRange) {
        this.sessionRange = sessionRange;
        return this;
    }

    public SessionBuilder withSessionPolicy(SessionPolicy sessionPolicy) {
        this.sessionPolicy = sessionPolicy;
        return this;
    }

    public SessionBuilder withSessionStatus(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
        return this;
    }

    public SessionBuilder withEnrollment(Enrollment enrollment) {
        this.enrollments.add(enrollment);
        return this;
    }

    public Session build() {
        return new Session(id, sessionRange, sessionPolicy, sessionStatus, coverImage, enrollments);
    }

}

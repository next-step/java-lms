package nextstep.courses.domain.session;

import nextstep.courses.domain.image.SessionCoverImage;
import nextstep.courses.domain.session.type.FreeType;
import nextstep.courses.domain.session.type.PaidType;
import nextstep.courses.domain.session.type.SessionType;

public class SessionBuilder {
    private Long id = null;
    private Long courseId = 1L;
    private Term term = new Term(1);
    private SessionPeriod period = new SessionPeriod("2025-01-01", "2025-01-31");
    private SessionState state = SessionState.PREPARING;
    private SessionType type = new FreeType();
    private SessionCoverImage coverImage = null;

    public static SessionBuilder aSession() {
        return new SessionBuilder();
    }

    public SessionBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public SessionBuilder withCourseId(Long courseId) {
        this.courseId = courseId;
        return this;
    }

    public SessionBuilder withTerm(int term) {
        this.term = new Term(term);
        return this;
    }

    public SessionBuilder withPeriod(String startDay, String endDay) {
        this.period = new SessionPeriod(startDay, endDay);
        return this;
    }

    public SessionBuilder withState(SessionState state) {
        this.state = state;
        return this;
    }

    public SessionBuilder withType(SessionType type) {
        this.type = type;
        return this;
    }

    public SessionBuilder withCoverImage(SessionCoverImage coverImage) {
        this.coverImage = coverImage;
        return this;
    }

    public SessionBuilder recruiting() {
        this.state = SessionState.RECRUITING;
        return this;
    }

    public SessionBuilder paid(int maxCapacity, long tuitionFee) {
        this.type = new PaidType(maxCapacity, tuitionFee);
        return this;
    }

    public Session build() {
        Enrollment enrollment = new Enrollment(state, type);
        return new Session(id, courseId, term, period, enrollment, coverImage);
    }
}
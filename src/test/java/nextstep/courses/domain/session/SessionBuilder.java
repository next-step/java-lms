package nextstep.courses.domain.session;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.image.SessionCoverImage;
import nextstep.courses.domain.session.type.FreeType;
import nextstep.courses.domain.session.type.SessionType;

public class SessionBuilder {
    private Long id = null;
    private Course course = new Course("TDD", 1L);
    private Term term = new Term(1);
    private SessionCoverImage cover = new SessionCoverImage(300, 200, "png", 1024 * 500);
    private SessionPeriod period = new SessionPeriod("2025-01-01", "2025-01-31");
    private SessionState state = SessionState.PREPARING;
    private SessionType type = new FreeType();

    public static SessionBuilder aSession() {
        return new SessionBuilder();
    }

    public SessionBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public SessionBuilder withCourse(Course course) {
        this.course = course;
        return this;
    }

    public SessionBuilder withTerm(int term) {
        this.term = new Term(term);
        return this;
    }

    public SessionBuilder withCover(SessionCoverImage cover) {
        this.cover = cover;
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

    public SessionBuilder recruiting() {
        this.state = SessionState.RECRUITING;
        return this;
    }

    public Session build() {
        Enrollment enrollment = new Enrollment(state, type);
        return new Session(id, course, term, cover, period, enrollment);
    }
}
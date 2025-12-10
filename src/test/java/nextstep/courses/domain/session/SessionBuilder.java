package nextstep.courses.domain.session;

import nextstep.courses.domain.image.SessionCoverImage;

public class SessionBuilder {
    private Long id = null;
    private Long courseId = 1L;
    private Term term = new Term(1);
    private SessionPeriod period = new SessionPeriod("2025-01-01", "2025-01-31");
    private SessionState state = SessionState.PREPARING;
  private SessionPolicy sessionPolicy = new SessionPolicy();
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

  public SessionBuilder withSessionPolicy(SessionPolicy sessionPolicy) {
    this.sessionPolicy = sessionPolicy;
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

  public SessionBuilder paid(long tuitionFee, int maxCapacity) {
    this.sessionPolicy = SessionPolicy.paid(tuitionFee, maxCapacity);
    return this;
  }

  public SessionBuilder free() {
    this.sessionPolicy = SessionPolicy.free();
        return this;
    }

    public Session build() {
      return sessionPolicy.createSession(id, courseId, term, period, state, coverImage);
    }
}
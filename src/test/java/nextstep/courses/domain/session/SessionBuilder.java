package nextstep.courses.domain.session;

import nextstep.courses.domain.image.SessionCoverImage;
import nextstep.courses.domain.image.SessionCoverImages;

public class SessionBuilder {
    private Long id = null;
    private Long courseId = 1L;
    private Term term = new Term(1);
    private SessionPeriod period = new SessionPeriod("2025-01-01", "2025-01-31");
    private SessionProgressState state = SessionProgressState.PREPARING;
  private SessionPolicy sessionPolicy = new SessionPolicy();
    private SessionCoverImages coverImages = null;

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

    public SessionBuilder withState(SessionProgressState state) {
        this.state = state;
        return this;
    }

  public SessionBuilder withSessionPolicy(SessionPolicy sessionPolicy) {
    this.sessionPolicy = sessionPolicy;
        return this;
    }

    public SessionBuilder withCoverImages(SessionCoverImages coverImages) {
        this.coverImages = coverImages;
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
      return sessionPolicy.createSession(id, courseId, term, period, state, coverImages);
    }
}
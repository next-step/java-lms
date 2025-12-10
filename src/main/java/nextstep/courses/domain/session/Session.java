package nextstep.courses.domain.session;

import java.util.List;
import nextstep.courses.domain.BaseEntity;
import nextstep.courses.domain.image.SessionCoverImage;
import nextstep.courses.domain.registration.Registration;
import nextstep.courses.domain.registration.Registrations;

public class Session extends BaseEntity {
    private final Long courseId;
    private final Term term;
    private final SessionPeriod period;
    private final SessionCoverImage coverImage;
    private final SessionPolicy sessionPolicy;
    private SessionState state;

    public Session(Long courseId, int term, String startDay, String endDay, SessionCoverImage coverImage) {
        this(null, courseId, new Term(term), new SessionPeriod(startDay, endDay), SessionState.PREPARING, new SessionPolicy(), coverImage);
    }

    public Session(Long courseId, int term, String startDay, String endDay, SessionPolicy sessionPolicy, SessionCoverImage coverImage) {
        this(null, courseId, new Term(term), new SessionPeriod(startDay, endDay), SessionState.PREPARING, sessionPolicy, coverImage);
    }

    public Session(Long id, Long courseId, Term term, SessionPeriod period, SessionState state, SessionPolicy sessionPolicy, SessionCoverImage coverImage) {
        super(id);
        this.courseId = courseId;
        this.term = term;
        this.period = period;
        this.state = state;
        this.sessionPolicy = sessionPolicy;
        this.coverImage = coverImage;
    }

    public Enrollment enrollment(Registrations registrations) {
      return new Enrollment(this, state, sessionPolicy, registrations);
    }

    public Enrollment enrollment(List<Registration> registrations) {
        return new Enrollment(this, state, sessionPolicy, new Registrations(registrations));
    }

    public void open() {
        this.state = state.open();
    }

    public void close() {
        this.state = state.close();
    }

    public Long getCourseId() {
        return courseId;
    }

    public Term getTerm() {
        return term;
    }

    public SessionPeriod getPeriod() {
        return period;
    }

    public SessionState getState() {
        return state;
    }

    public SessionPolicy getSessionPolicy() {
        return sessionPolicy;
    }

    public SessionCoverImage getCoverImage() {
        return coverImage;
    }
}

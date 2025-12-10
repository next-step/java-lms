package nextstep.courses.domain.session;

import java.util.List;
import nextstep.courses.domain.BaseEntity;
import nextstep.courses.domain.image.SessionCoverImage;
import nextstep.courses.domain.image.SessionCoverImages;
import nextstep.courses.domain.registration.Registration;
import nextstep.courses.domain.registration.Registrations;

public class Session extends BaseEntity {
    private final Long courseId;
    private final Term term;
    private final SessionPeriod period;
    private final SessionCoverImages coverImages;
    private final SessionPolicy sessionPolicy;
    private SessionState state;

    public Session(Long courseId, int term, String startDay, String endDay, SessionCoverImages coverImages) {
        this(null, courseId, new Term(term), new SessionPeriod(startDay, endDay), SessionState.PREPARING, new SessionPolicy(), coverImages);
    }

    public Session(Long courseId, int term, String startDay, String endDay, SessionPolicy sessionPolicy, SessionCoverImages coverImages) {
        this(null, courseId, new Term(term), new SessionPeriod(startDay, endDay), SessionState.PREPARING, sessionPolicy, coverImages);
    }

    public Session(Long id, Long courseId, Term term, SessionPeriod period, SessionState state, SessionPolicy sessionPolicy, SessionCoverImages coverImages) {
        super(id);
        this.courseId = courseId;
        this.term = term;
        this.period = period;
        this.state = state;
        this.sessionPolicy = sessionPolicy;
        this.coverImages = coverImages;
    }

    public Enrollment enrollment(Registrations registrations) {
      return new Enrollment(this.getId(), state, sessionPolicy, registrations);
    }

    public Enrollment enrollment(List<Registration> registrations) {
        return new Enrollment(this.getId(), state, sessionPolicy, new Registrations(registrations));
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

    public SessionCoverImages getCoverImages() {
        return coverImages;
    }
}

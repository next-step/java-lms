package nextstep.courses.domain.session;

import java.util.List;
import nextstep.courses.domain.BaseEntity;
import nextstep.courses.domain.image.SessionCoverImages;
import nextstep.courses.domain.registration.Registration;
import nextstep.courses.domain.registration.Registrations;
import nextstep.courses.domain.session.recruit.Recruiting;

public class Session extends BaseEntity {
    private final Long courseId;
    private final Term term;
    private final SessionPeriod period;
    private final SessionCoverImages coverImages;
    private final SessionPolicy sessionPolicy;
    private SessionProgressState progressState;

    public Session(Long courseId, int term, String startDay, String endDay, SessionCoverImages coverImages) {
        this(null, courseId, new Term(term), new SessionPeriod(startDay, endDay), SessionProgressState.PREPARING, new SessionPolicy(), coverImages);
    }

    public Session(Long courseId, int term, String startDay, String endDay, SessionPolicy sessionPolicy, SessionCoverImages coverImages) {
        this(null, courseId, new Term(term), new SessionPeriod(startDay, endDay), SessionProgressState.PREPARING, sessionPolicy, coverImages);
    }

    public Session(Long id, Long courseId, Term term, SessionPeriod period, SessionProgressState progressState, SessionPolicy sessionPolicy, SessionCoverImages coverImages) {
        super(id);
        this.courseId = courseId;
        this.term = term;
        this.period = period;
        this.progressState = progressState;
        this.sessionPolicy = sessionPolicy;
        this.coverImages = coverImages;
    }
    public Enrollment enrollment(Registration registration) {
      validateCanCreateEnrollment();
      return new Enrollment(this.getId(), new Recruiting(), sessionPolicy, new Registrations(registration));
    }

    public Enrollment enrollment(Registrations registrations) {
        validateCanCreateEnrollment();
        return new Enrollment(this.getId(), new Recruiting(), sessionPolicy, registrations);
    }

    public Enrollment enrollment(List<Registration> registrations) {
        validateCanCreateEnrollment();
        return new Enrollment(this.getId(), new Recruiting(), sessionPolicy, new Registrations(registrations));
    }

    private void validateCanCreateEnrollment() {
        if (!progressState.canCreateEnrollment()) {
            throw new IllegalStateException("종료된 강의는 수강신청을 할 수 없습니다.");
        }
    }

    public void start() {
        this.progressState = progressState.start();
    }

    public void finish() {
        this.progressState = progressState.finish();
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

    public SessionProgressState getProgressState() {
        return progressState;
    }

    public SessionPolicy getSessionPolicy() {
        return sessionPolicy;
    }

    public SessionCoverImages getCoverImages() {
        return coverImages;
    }
}

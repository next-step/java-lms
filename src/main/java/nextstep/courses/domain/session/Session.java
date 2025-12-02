package nextstep.courses.domain.session;

import nextstep.courses.domain.BaseEntity;
import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.image.SessionCoverImage;
import nextstep.courses.domain.session.type.FreeType;
import nextstep.courses.domain.session.type.SessionType;

public class Session extends BaseEntity {
  private final Course course;
  private final Term term;
  private final SessionCoverImage cover;
  private final SessionPeriod period;
  private final SessionState state;
  private final SessionType type;

  public Session(Course course, int term, SessionCoverImage cover, String startDay, String endDay) {
    this(null, course, new Term(term), cover, new SessionPeriod(startDay, endDay), new FreeType());
  }

  public Session(Long id, Course course, Term term, SessionCoverImage cover, SessionPeriod period, SessionType type) {
    this(id, course, term, cover, period, type, SessionState.PREPARING);
  }

  public Session(Long id, Course course, Term term, SessionCoverImage cover, SessionPeriod period, SessionType type, SessionState state) {
    super(id);
    this.course = course;
    this.term = term;
    this.cover = cover;
    this.period = period;
    this.type = type;
    this.state = state;
  }

  public Session enroll(int payAmount) {
    validateState();
    return new Session(getId(), course, term, cover, period, type.enroll(payAmount), state);
  }

  public Session openEnrollment() {
    if (state != SessionState.PREPARING) {
      throw new IllegalStateException("준비중인 강의만 모집을 시작할 수 있습니다.");
    }
    return new Session(getId(), course, term, cover, period, type, SessionState.RECRUITING);
  }

  public Session closeEnrollment() {
    if (state != SessionState.RECRUITING) {
      throw new IllegalStateException("모집중인 강의만 종료할 수 있습니다.");
    }
    return new Session(getId(), course, term, cover, period, type, SessionState.CLOSED);
  }

  private void validateState() {
    if (!state.canEnroll()) {
      throw new IllegalStateException("모집중인 강의만 수강신청이 가능합니다.");
    }
  }
}

package nextstep.courses.domain;

public class Session extends BaseEntity {
  private final Course course;
  private final int term;
  private final SessionCoverImage cover;
  private final SessionPeriod period;
  private final RecruitmentState state;
  private final SessionEnrollment enrollment;

  public Session(Course course, int term, SessionCoverImage cover, String startDay, String endDay) {
    this(null, course, term, cover, new SessionPeriod(startDay, endDay), SessionEnrollment.free());
  }

  public Session(Course course, int term, SessionCoverImage cover, String startDay, String endDay, int maxCapacity, int tuitionFee) {
    this(null, course, term, cover, new SessionPeriod(startDay, endDay), SessionEnrollment.paid(maxCapacity, tuitionFee));
  }

  public Session(Course course, int term, SessionCoverImage cover, String startDay, String endDay, int maxCapacity, int tuitionFee, int studentCount) {
    this(null, course, term, cover, new SessionPeriod(startDay, endDay), new SessionEnrollment(maxCapacity, tuitionFee, studentCount));
  }

  public Session(Long id, Course course, int term, SessionCoverImage cover, SessionPeriod period, SessionEnrollment enrollment) {
    this(id, course, term, cover, period, enrollment, RecruitmentState.PREPARING);
  }

  public Session(Long id, Course course, int term, SessionCoverImage cover, SessionPeriod period, SessionEnrollment enrollment, RecruitmentState state) {
    super(id);
    this.course = course;
    this.term = term;
    this.cover = cover;
    this.period = period;
    this.enrollment = enrollment;
    this.state = state;
  }

  public Session enroll() {
    return enroll(0);
  }

  public Session enroll(int payAmount) {
    validateState();
    return new Session(getId(), course, term, cover, period, enrollment.enroll(payAmount), state);
  }

  public Session openEnrollment() {
    if (state != RecruitmentState.PREPARING) {
      throw new IllegalStateException("준비중인 강의만 모집을 시작할 수 있습니다.");
    }
    return new Session(getId(), course, term, cover, period, enrollment, RecruitmentState.RECRUITING);
  }

  public Session closeEnrollment() {
    if (state != RecruitmentState.RECRUITING) {
      throw new IllegalStateException("모집중인 강의만 종료할 수 있습니다.");
    }
    return new Session(getId(), course, term, cover, period, enrollment, RecruitmentState.CLOSED);
  }

  public RecruitmentState getState() {
    return state;
  }

  private void validateState() {
    if (!state.canEnroll()) {
      throw new IllegalStateException("모집중인 강의만 수강신청이 가능합니다.");
    }
  }
}

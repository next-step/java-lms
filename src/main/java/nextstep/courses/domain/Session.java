package nextstep.courses.domain;

public class Session {
  private final Course course;
  private final SessionCoverImage coverImage;
  private final SessionPeriod period;
  private final RecruitmentState state;

  private final int maxCapacity;
  private final int tuitionFee;
  private final int studentCount;

  public Session(Course course, SessionCoverImage coverImage, String startDay, String endDay) {
    this(course, coverImage, startDay, endDay, Integer.MAX_VALUE, 0);
  }

  public Session(Course course, SessionCoverImage coverImage, String startDay, String endDay, int maxCapacity, int tuitionFee) {
    this(course, coverImage, new SessionPeriod(startDay, endDay), maxCapacity, tuitionFee, 0);
  }

  public Session(Course course, SessionCoverImage coverImage, String startDay, String endDay, int maxCapacity, int tuitionFee, int studentCount) {
    this(course, coverImage, new SessionPeriod(startDay, endDay), maxCapacity, tuitionFee, studentCount);
  }

  public Session(Course course, SessionCoverImage coverImage, SessionPeriod period, int maxCapacity, int tuitionFee, int studentCount) {
    this(course, coverImage, period, maxCapacity, tuitionFee, studentCount, RecruitmentState.PREPARING);
  }

  public Session(Course course, SessionCoverImage coverImage, SessionPeriod period, int maxCapacity, int tuitionFee, int studentCount, RecruitmentState state) {
    validateCapacity(maxCapacity, studentCount);
    this.course = course;
    this.coverImage = coverImage;
    this.period = period;
    this.maxCapacity = maxCapacity;
    this.tuitionFee = tuitionFee;
    this.studentCount = studentCount;
    this.state = state;
  }

  public Session enroll(){
    return this.enroll(0);
  }

  public Session enroll(int payAmount) {
    validateState();
    validateCapacity(maxCapacity, studentCount + 1);
    validateTuitionFee(payAmount);
    return increaseStudent();
  }

  public Session openEnrollment() {
    if (state != RecruitmentState.PREPARING) {
      throw new IllegalStateException("준비중인 강의만 모집을 시작할 수 있습니다.");
    }
    return new Session(this.course, this.coverImage, this.period, this.maxCapacity, this.tuitionFee, this.studentCount, RecruitmentState.RECRUITING);
  }

  public Session closeEnrollment() {
    if (state != RecruitmentState.RECRUITING) {
      throw new IllegalStateException("모집중인 강의만 종료할 수 있습니다.");
    }
    return new Session(this.course, this.coverImage, this.period, this.maxCapacity, this.tuitionFee, this.studentCount, RecruitmentState.CLOSED);
  }

  public RecruitmentState getState() {
    return state;
  }

  private Session increaseStudent() {
    return new Session(this.course, this.coverImage, this.period, this.maxCapacity, this.tuitionFee, this.studentCount + 1, this.state);
  }

  private void validateState() {
    if (!state.canEnroll()) {
      throw new IllegalStateException("모집중인 강의만 수강신청이 가능합니다.");
    }
  }

  private void validateTuitionFee(int payAmount){
    if(payAmount != tuitionFee){
      throw new IllegalArgumentException("수강료와 지불한 금액이 정확히 일치해야 합니다.");
    }
  }

  private void validateCapacity(int maxCapacity, int studentCount){
    if(maxCapacity < studentCount){
      throw new IllegalArgumentException("최대 수강 인원을 초과할 수 없습니다.");
    }
  }

}

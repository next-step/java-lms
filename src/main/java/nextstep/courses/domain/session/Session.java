package nextstep.courses.domain.session;

import nextstep.courses.domain.BaseEntity;
import nextstep.courses.domain.image.SessionCoverImage;
import nextstep.courses.domain.session.type.SessionType;

public class Session extends BaseEntity {
  private final Long courseId;
  private final Term term;
  private final SessionPeriod period;
  private final Enrollment enrollment;
  private final SessionCoverImage coverImage;

  public Session(Long courseId, int term, String startDay, String endDay, SessionCoverImage coverImage) {
    this(null, courseId, new Term(term), new SessionPeriod(startDay, endDay), new Enrollment(), coverImage);
  }

  public Session(Long courseId, int term, String startDay, String endDay, SessionType type, SessionCoverImage coverImage) {
    this(null, courseId, new Term(term), new SessionPeriod(startDay, endDay), new Enrollment(type), coverImage);
  }

  public Session(Long id, Long courseId, Term term, SessionPeriod period, Enrollment enrollment, SessionCoverImage coverImage) {
    super(id);
    this.courseId = courseId;
    this.term = term;
    this.period = period;
    this.enrollment = enrollment;
    this.coverImage = coverImage;
  }

  public void validateEnroll(long payAmount) {
    enrollment.validateEnroll(payAmount);
  }

  public void open() {
    enrollment.open();
  }

  public void close() {
    enrollment.close();
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

  public Enrollment getEnrollment() {
    return enrollment;
  }

  public SessionState getState() {
    return enrollment.getState();
  }

  public SessionCoverImage getCoverImage() {
    return coverImage;
  }
}

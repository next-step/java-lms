package nextstep.courses.domain.session;

import nextstep.courses.domain.BaseEntity;
import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.image.SessionCoverImage;

public class Session extends BaseEntity {
  private final Course course;
  private final Term term;
  private final SessionCoverImage cover;
  private final SessionPeriod period;
  private final Enrollment enrollment;

  public Session(Course course, int term, SessionCoverImage cover, String startDay, String endDay) {
    this(null, course, new Term(term), cover, new SessionPeriod(startDay, endDay), new Enrollment());
  }

  public Session(Long id, Course course, Term term, SessionCoverImage cover, SessionPeriod period, Enrollment enrollment) {
    super(id);
    this.course = course;
    this.term = term;
    this.cover = cover;
    this.period = period;
    this.enrollment = enrollment;
  }

  public void enroll(long payAmount) {
    enrollment.enroll(payAmount);
  }

  public void open() {
    enrollment.open();
  }

  public void close() {
    enrollment.close();
  }
}

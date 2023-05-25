package nextstep.courses.domain;

import java.time.LocalDateTime;
import nextstep.qna.NotFoundException;

public class Session {

  private Long id;

  private String title;

  private String img;

  private LocalDateTime startDate;

  private LocalDateTime endDate;

  private SessionStatus sessionStatus = SessionStatus.PREPARATION;

  private int maxRecruitment;

  private Course course;

  public Session() {
  }

  public Session(String title, LocalDateTime startDate, LocalDateTime endDate, String img,
      int maxRecruitment, Course course) {
    this(null, title, startDate, endDate, img, maxRecruitment, course);
  }

  public Session(Long id, String title, LocalDateTime startDate, LocalDateTime endDate,
      String img, int maxRecruitment, Course course) {
    this.id = id;
    this.title = title;
    validateDate(startDate, endDate);
    this.startDate = startDate;
    this.endDate = endDate;
    this.img = img;
    validateMaxRecruitment(maxRecruitment);
    this.maxRecruitment = maxRecruitment;
    validateCourse(course);
    this.course = course;
  }

  private void validateMaxRecruitment(int maxRecruitment) {
    if(maxRecruitment < 1){
      throw new IllegalArgumentException();
    }
  }

  private void validateDate(LocalDateTime startDate, LocalDateTime endDate) {
    if (startDate == null || endDate == null) {
      throw new IllegalArgumentException();
    }

    if (endDate.isBefore(startDate)) {
      throw new IllegalArgumentException();
    }
  }

  private void validateCourse(Course course) {
    if (course == null) {
      throw new NotFoundException();
    }
  }

  public void toCourse(Course course) {
    this.course = course;
  }
}

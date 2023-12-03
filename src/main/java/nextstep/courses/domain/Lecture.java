package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import nextstep.users.domain.NsUser;

public class Lecture {
  private final LectureType lectureType;
  private final LocalDateTime startDateTime;
  private final LocalDateTime endDatetime;
  private final Integer limitStudentCount;
  private final List<NsUser> students = new ArrayList<>();

  private Lecture(LectureType lectureType, LocalDateTime startDateTime,
      LocalDateTime endDateTime, int limitStudentCount) {
    this.lectureType = lectureType;
    this.startDateTime = startDateTime;
    this.endDatetime = endDateTime;
    this.limitStudentCount = limitStudentCount;
  }

  public static Lecture freeOf(LectureType lectureType, LocalDateTime startDateTime,
      LocalDateTime endDateTime) {
    return new Lecture(lectureType, startDateTime, endDateTime, 0);
  }

  public static Lecture paidOf(LectureType lectureType, LocalDateTime startDateTime,
      LocalDateTime endDateTime, int maxStudent) {
    return new Lecture(lectureType, startDateTime, endDateTime, maxStudent);
  }

  public boolean isFree() {
    return lectureType == LectureType.FREE;
  }
  public void canEnrolment() {
    if (this.lectureType == LectureType.PAID && this.limitStudentCount <= students.size()) {
      throw new IllegalArgumentException("수강인원이 가득찼습니다.");
    }
  }

  public void enrolment(NsUser user) {
    this.canEnrolment();
    students.add(user);
  }

  public Integer studentCount() {
    return students.size();
  }
}

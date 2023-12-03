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
  private final List<NsUser> numberOfStudent = new ArrayList<>();

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
}

package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.List;
import nextstep.users.domain.NsUser;

public class Lecture {

  private final LectureType lectureType;
  private final LectureStatus lectureStatus;
  private final LocalDateTime startedAt;
  private final LocalDateTime endedAt;
  private final Integer limitStudentCount;
  private final NsUsers students;

  private Lecture(LectureType lectureType, LocalDateTime startedAt,
      LocalDateTime endDateTime, int limitStudentCount) {
    this.lectureType = lectureType;
    this.startedAt = startedAt;
    this.endedAt = endDateTime;
    this.limitStudentCount = limitStudentCount;
    this.lectureStatus = LectureStatus.PREPARING;
    this.students = NsUsers.defaultOf();
  }

  private Lecture(LectureType lectureType, LectureStatus lectureStatus, LocalDateTime startedAt,
      LocalDateTime endDateTime, int limitStudentCount, NsUsers students) {
    this.lectureType = lectureType;
    this.startedAt = startedAt;
    this.endedAt = endDateTime;
    this.limitStudentCount = limitStudentCount;
    this.lectureStatus = lectureStatus;
    this.students = students;
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

  private void canEnrolment() {
    if (this.lectureType == LectureType.PAID && this.limitStudentCount <= students.size()) {
      throw new IllegalArgumentException("수강인원이 가득찼습니다.");
    }

    if (this.lectureStatus != LectureStatus.RECRUITING) {
      throw new IllegalArgumentException("이 강의는 수강신청이 불가능합니다.");
    }
  }

  public void enrolment(NsUser user) {
    this.canEnrolment();
    students.add(user);
  }

  public Integer studentCount() {
    return students.size();
  }

  public Lecture recruit() {
    return new Lecture(this.lectureType, LectureStatus.RECRUITING, this.startedAt,
        this.endedAt, this.limitStudentCount, this.students);
  }
}

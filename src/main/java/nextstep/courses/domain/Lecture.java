package nextstep.courses.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.naming.LimitExceededException;
import nextstep.users.domain.NsUser;

public class Lecture {
  private final Long id;
  private final String title;
  private final CoverImage coverImage;
  private final BigDecimal price; // LectureBasicInformation
  private final Integer limitStudentCount; // LectureBasicInformation
  private final LectureType lectureType; // LectureBasicInformation
  private final LectureStatus lectureStatus; // LectureBasicInformation
  private final LocalDateTime startedAt; // LectureBasicInformation
  private final LocalDateTime endedAt; // LectureBasicInformation

  private final NsUsers students; // 강의 기본정보와는 다름

  private Lecture(String title, CoverImage coverImage,LectureType lectureType, LocalDateTime startedAt,
      LocalDateTime endDateTime, int limitStudentCount) {
    this.id = 0L;
    this.title = title;
    this.coverImage = coverImage;
    this.price = BigDecimal.ZERO;
    this.lectureType = lectureType;
    this.startedAt = startedAt;
    this.endedAt = endDateTime;
    this.limitStudentCount = limitStudentCount;
    this.lectureStatus = LectureStatus.PREPARING;
    this.students = NsUsers.defaultOf();
  }

  private Lecture(String title, CoverImage coverImage ,BigDecimal price, LectureType lectureType, LectureStatus lectureStatus, LocalDateTime startedAt,
      LocalDateTime endDateTime, int limitStudentCount, NsUsers students) {
    this.id = 0L;
    this.title = title;
    this.coverImage = coverImage;
    this.price = price;
    this.lectureType = lectureType;
    this.startedAt = startedAt;
    this.endedAt = endDateTime;
    this.limitStudentCount = limitStudentCount;
    this.lectureStatus = lectureStatus;
    this.students = students;
  }

  private Lecture(String title, CoverImage coverImage, BigDecimal price, LectureType lectureType, LocalDateTime startedAt,
      LocalDateTime endDateTime, int limitStudentCount, NsUsers students) {
    this.id = 0L;
    this.title = title;
    this.coverImage = coverImage;
    this.price = price;
    this.lectureType = lectureType;
    this.startedAt = startedAt;
    this.endedAt = endDateTime;
    this.limitStudentCount = limitStudentCount;
    this.lectureStatus = LectureStatus.PREPARING;
    this.students = students;
  }


  public static Lecture freeOf(String title, CoverImage coverImage,LectureType lectureType, LocalDateTime startDateTime,
      LocalDateTime endDateTime) {
    return new Lecture(title, coverImage,lectureType, startDateTime, endDateTime, 0);
  }

  public static Lecture paidOf(String title, CoverImage coverImage,BigDecimal price,LectureType lectureType, LocalDateTime startDateTime,
      LocalDateTime endDateTime, int maxStudent) {
    return new Lecture(title,coverImage,price, lectureType, startDateTime, endDateTime, maxStudent, NsUsers.defaultOf());
  }

  public boolean isFree() {
    return lectureType == LectureType.FREE;
  }

  private void canEnrolment(NsUser user) {
    if (this.lectureType == LectureType.PAID && this.limitStudentCount <= students.size()) {
      throw new IllegalArgumentException("수강인원이 가득찼습니다.");
    }

    if (this.lectureStatus != LectureStatus.RECRUITING) {
      throw new IllegalArgumentException("이 강의는 수강신청이 불가능합니다.");
    }

    if (!user.hasPayment(this.price)) {
      throw new IllegalArgumentException("수강료를 지불해야합니다.");
    }
  }

  public void enrolment(NsUser user) {
    this.canEnrolment(user);
    students.add(user);
  }

  public Integer numberOfStudent() {
    return students.size();
  }

  public Lecture start() {
    return new Lecture(this.title, this.coverImage, this.price, this.lectureType, LectureStatus.RECRUITING, this.startedAt,
        this.endedAt, this.limitStudentCount, this.students);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Lecture lecture = (Lecture) o;

    return id.equals(lecture.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }
}

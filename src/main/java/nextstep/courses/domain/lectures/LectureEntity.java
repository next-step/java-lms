package nextstep.courses.domain.lectures;

import java.time.LocalDateTime;
import nextstep.courses.Time;
import nextstep.courses.domain.coverimage.CoverImage;
import nextstep.users.domain.Price;

public class LectureEntity extends Time  {
  private final LectureType lectureType;
  private final Long id;
  private final String title;
  private final CoverImage coverImage;
  private final LectureStatus lectureStatus;
  private final RegistrationPeriod registrationPeriod;
  private final Price price;
  private final Integer limitStudentCount;

  public LectureEntity(LectureType lectureType, Long id, String title, CoverImage coverImage,
      LectureStatus lectureStatus, RegistrationPeriod registrationPeriod, Price price,
      Integer limitStudentCount, LocalDateTime createdAt, LocalDateTime updatedAt) {
    super(createdAt, updatedAt);
    this.lectureType = lectureType;
    this.id = id;
    this.title = title;
    this.coverImage = coverImage;
    this.lectureStatus = lectureStatus;
    this.registrationPeriod = registrationPeriod;
    this.price = price;
    this.limitStudentCount = limitStudentCount;
  }
}

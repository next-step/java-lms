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

  public LectureEntity(Long id,   String title, CoverImage coverImage, LectureType lectureType,
      LectureStatus lectureStatus, RegistrationPeriod registrationPeriod, Price price,
      Integer limitStudentCount, LocalDateTime createdAt, LocalDateTime updatedAt) {
    super(createdAt, updatedAt);
    this.id = id;
    this.title = title;
    this.coverImage = coverImage;
    this.lectureType = lectureType;
    this.lectureStatus = lectureStatus;
    this.registrationPeriod = registrationPeriod;
    this.price = price;
    this.limitStudentCount = limitStudentCount;
  }

  public LectureType getLectureType() {
    return lectureType;
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public CoverImage getCoverImage() {
    return coverImage;
  }

  public LectureStatus getLectureStatus() {
    return lectureStatus;
  }

  public RegistrationPeriod getRegistrationPeriod() {
    return registrationPeriod;
  }

  public Price getPrice() {
    return price;
  }

  public Integer getLimitStudentCount() {
    return limitStudentCount;
  }
}

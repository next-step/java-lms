package nextstep.courses.domain.lectures;

import java.time.LocalDateTime;
import nextstep.courses.BaseTime;
import nextstep.courses.domain.coverimage.CoverImages;
import nextstep.users.domain.Price;

public class LectureEntity extends BaseTime {
  private final LectureType lectureType;
  private final Long id;
  private final String title;
  private final CoverImages coverImages = new CoverImages();
  private final Lecture
  private final LectureRecruitingStatus lectureRecruitingStatus;
  private final RegistrationPeriod registrationPeriod;
  private final Price price;
  private final Integer limitStudentCount;

  public LectureEntity(Long id,   String title, CoverImages coverImages, LectureType lectureType,
      LectureRecruitingStatus lectureRecruitingStatus, RegistrationPeriod registrationPeriod, Price price,
      Integer limitStudentCount, LocalDateTime createdAt, LocalDateTime updatedAt) {
    super(createdAt, updatedAt);
    this.id = id;
    this.title = title;
    this.coverImages.addAll(coverImages);
    this.lectureType = lectureType;
    this.lectureRecruitingStatus = lectureRecruitingStatus;
    this.registrationPeriod = registrationPeriod;
    this.price = price;
    this.limitStudentCount = limitStudentCount;
  }

  public LectureType lectureType() {
    return lectureType;
  }

  public Long id() {
    return id;
  }

  public String title() {
    return title;
  }

  public CoverImages coverImage() {
    return coverImages;
  }

  public LectureRecruitingStatus lectureStatus() {
    return lectureRecruitingStatus;
  }

  public RegistrationPeriod registrationPeriod() {
    return registrationPeriod;
  }

  public Price price() {
    return price;
  }

  public Integer limitStudentCount() {
    return limitStudentCount;
  }
}

package nextstep.courses.domain.lectures;

import java.time.LocalDateTime;
import nextstep.courses.BaseTime;
import nextstep.courses.domain.coverimage.CoverImage;
import nextstep.courses.domain.Students;
import nextstep.courses.domain.coverimage.CoverImages;
import nextstep.users.domain.NsUser;

public class FreeLecture extends BaseTime implements Lecture {
  private final LectureType lectureType = LectureType.FREE;
  private final Long id;
  private final String title;
  private final CoverImages coverImages = new CoverImages();
  private final LectureRecruitingStatus lectureRecruitingStatus;
  private final RegistrationPeriod registrationPeriod;
  private final Students students = Students.defaultOf(); // 강의 기본정보와는 다름

  public FreeLecture(Long id, String title, CoverImage coverImage, LectureRecruitingStatus lectureRecruitingStatus,
      RegistrationPeriod registrationPeriod) {
    super();
    this.id = id;
    this.title = title;
    this.coverImages.add(coverImage);
    this.lectureRecruitingStatus = lectureRecruitingStatus;
    this.registrationPeriod = registrationPeriod;
  }

  public FreeLecture(Long id, String title, CoverImages coverImages, LectureRecruitingStatus lectureRecruitingStatus,
      RegistrationPeriod registrationPeriod) {
    super();
    this.id = id;
    this.title = title;
    this.coverImages.addAll(coverImages);
    this.lectureRecruitingStatus = lectureRecruitingStatus;
    this.registrationPeriod = registrationPeriod;
  }
  public FreeLecture(Long id, String title, CoverImage coverImage, LectureRecruitingStatus lectureRecruitingStatus,
      RegistrationPeriod registrationPeriod, LocalDateTime createdAt, LocalDateTime updatedAt) {
    super(createdAt, updatedAt);
    this.id = id;
    this.title = title;
    this.coverImages.add(coverImage);
    this.lectureRecruitingStatus = lectureRecruitingStatus;
    this.registrationPeriod = registrationPeriod;
  }
  public FreeLecture(LectureEntity lecture) {
    super(lecture.getCreatedAt(), lecture.getUpdatedAt());
    this.id = lecture.id();
    this.title = lecture.title();
    this.coverImages.addAll(lecture.coverImage());
    this.lectureRecruitingStatus = lecture.lectureStatus();
    this.registrationPeriod = lecture.registrationPeriod();
  }

  @Override
  public boolean isFree() {
    return LectureType.FREE.equals(this.lectureType);
  }

  @Override
  public boolean recruiting() {
    return LectureRecruitingStatus.RECRUITING.equals(this.lectureRecruitingStatus);
  }

  @Override
  public void canEnrollment() {
    if (!recruiting()) {
      throw new IllegalArgumentException("모집중이지 않습니다.");
    }
  }

  @Override
  public void enrollment(NsUser nsUser) {
    this.canEnrollment();
    students.add(nsUser);
  }

  @Override
  public Lecture start() {
    return new FreeLecture(this.id, this.title, this.coverImages, LectureRecruitingStatus.RECRUITING, this.registrationPeriod);
  }

  @Override
  public Integer numberOfStudent() {
    return students.size();
  }


  public LectureEntity toEntity() {
    return new LectureEntity(
        this.id
        , this.title
        , this.coverImages
        , this.lectureType
        , this.lectureRecruitingStatus
        , this.registrationPeriod
        , null
        , null
        , super.getCreatedAt()
        , super.getUpdatedAt()
    );
  }
}

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
  private final LectureStatus lectureStatus;
  private final LectureRecruitingStatus lectureRecruitingStatus;
  private final RegistrationPeriod registrationPeriod;
  private final Students students = Students.defaultOf(); // 강의 기본정보와는 다름

  public FreeLecture(Long id, String title, CoverImages coverImages,
      LectureRecruitingStatus lectureRecruitingStatus,
      RegistrationPeriod registrationPeriod) {
    super();
    this.id = id;
    this.title = title;
    this.coverImages.addAll(coverImages);
    this.lectureStatus = LectureStatus.YET;
    this.lectureRecruitingStatus = lectureRecruitingStatus;
    this.registrationPeriod = registrationPeriod;
  }

  public FreeLecture(Long id, String title, CoverImages coverImages, LectureStatus lectureStatus,
      LectureRecruitingStatus lectureRecruitingStatus,
      RegistrationPeriod registrationPeriod) {
    super();
    this.id = id;
    this.title = title;
    this.coverImages.addAll(coverImages);
    this.lectureStatus = lectureStatus;
    this.lectureRecruitingStatus = lectureRecruitingStatus;
    this.registrationPeriod = registrationPeriod;
  }

  public FreeLecture(Long id, String title, CoverImage coverImage, LectureStatus lectureStatus,
      LectureRecruitingStatus lectureRecruitingStatus,
      RegistrationPeriod registrationPeriod, LocalDateTime createdAt, LocalDateTime updatedAt) {
    super(createdAt, updatedAt);
    this.id = id;
    this.title = title;
    this.coverImages.add(coverImage);
    this.lectureStatus = lectureStatus;
    this.lectureRecruitingStatus = lectureRecruitingStatus;
    this.registrationPeriod = registrationPeriod;
  }

  public FreeLecture(LectureEntity lecture) {
    super(lecture.getCreatedAt(), lecture.getUpdatedAt());
    this.id = lecture.id();
    this.title = lecture.title();
    this.coverImages.addAll(lecture.coverImage());
    this.lectureStatus = lecture.lectureStatus();
    this.lectureRecruitingStatus = lecture.lectureRecruitingStatus();
    this.registrationPeriod = lecture.registrationPeriod();
  }

  @Override
  public boolean isFree() {
    return LectureType.FREE.equals(this.lectureType);
  }

  @Override
  public boolean isRecruiting() {
    return LectureRecruitingStatus.RECRUITING.equals(this.lectureRecruitingStatus);
  }

  @Override
  public void canEnrollment(NsUser nsUser, Students selectedStudents) {
    if (!isRecruiting()) {
      throw new IllegalArgumentException("모집중이지 않습니다.");
    }

    if (selectedStudents.contain(nsUser)) {
      throw new IllegalArgumentException("선발된 인원만 수강신청이 가능합니다.");
    }
  }

  @Override
  public void enrollment(NsUser nsUser, Students selectedStudents) {
    this.canEnrollment(nsUser,selectedStudents);
    this.students.add(nsUser);
  }

  @Override
  public Lecture recruitingStart() {
    return new FreeLecture(this.id, this.title, this.coverImages,
        LectureRecruitingStatus.RECRUITING, this.registrationPeriod);
  }

  @Override
  public Lecture start() {
    return new FreeLecture(this.id, this.title, this.coverImages, LectureStatus.DOING,
        LectureRecruitingStatus.RECRUITING, this.registrationPeriod);
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
        , this.lectureStatus
        , this.lectureRecruitingStatus
        , this.registrationPeriod
        , null
        , null
        , super.getCreatedAt()
        , super.getUpdatedAt()
    );
  }
}

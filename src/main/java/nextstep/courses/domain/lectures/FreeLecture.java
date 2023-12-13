package nextstep.courses.domain.lectures;

import java.time.LocalDateTime;
import nextstep.courses.BaseTime;
import nextstep.courses.domain.coverimage.CoverImage;
import nextstep.courses.domain.Students;
import nextstep.users.domain.NsUser;

public class FreeLecture extends BaseTime implements Lecture {
  private final LectureType lectureType = LectureType.FREE;
  private final Long id;
  private final String title;
  private final CoverImage coverImage;
  private final LectureStatus lectureStatus;
  private final RegistrationPeriod registrationPeriod;
  private final Students students = Students.defaultOf(); // 강의 기본정보와는 다름

  public FreeLecture(Long id, String title, CoverImage coverImage, LectureStatus lectureStatus,
      RegistrationPeriod registrationPeriod) {
    super();
    this.id = id;
    this.title = title;
    this.coverImage = coverImage;
    this.lectureStatus = lectureStatus;
    this.registrationPeriod = registrationPeriod;
  }
  public FreeLecture(Long id, String title, CoverImage coverImage, LectureStatus lectureStatus,
      RegistrationPeriod registrationPeriod, LocalDateTime createdAt, LocalDateTime updatedAt) {
    super(createdAt, updatedAt);
    this.id = id;
    this.title = title;
    this.coverImage = coverImage;
    this.lectureStatus = lectureStatus;
    this.registrationPeriod = registrationPeriod;
  }
  public FreeLecture(LectureEntity lecture) {
    super(lecture.getCreatedAt(), lecture.getUpdatedAt());
    this.id = lecture.getId();
    this.title = lecture.getTitle();
    this.coverImage = lecture.getCoverImage();
    this.lectureStatus = lecture.getLectureStatus();
    this.registrationPeriod = lecture.getRegistrationPeriod();
  }

  @Override
  public boolean isFree() {
    return LectureType.FREE.equals(this.lectureType);
  }

  @Override
  public boolean recruiting() {
    return LectureStatus.RECRUITING.equals(this.lectureStatus);
  }

  @Override
  public void enrollment(NsUser nsUser) {
    if (recruiting()) {
      throw new IllegalArgumentException("모집중이지 않습니다.");
    }
    students.add(nsUser);
  }

  @Override
  public Lecture start() {
    return new FreeLecture(this.id, this.title, this.coverImage, LectureStatus.RECRUITING, this.registrationPeriod);
  }

  @Override
  public Integer numberOfStudent() {
    return students.size();
  }

  public LectureEntity toEntity() {
    return new LectureEntity(
        this.id
        , this.title
        , this.coverImage
        , this.lectureType
        , this.lectureStatus
        , this.registrationPeriod
        , null
        , null
        , super.getCreatedAt()
        , super.getUpdatedAt()
    );
  }
}

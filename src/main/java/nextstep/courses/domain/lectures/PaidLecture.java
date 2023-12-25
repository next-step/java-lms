package nextstep.courses.domain.lectures;

import java.time.LocalDateTime;
import nextstep.courses.BaseTime;
import nextstep.courses.domain.Students;
import nextstep.courses.domain.coverimage.CoverImage;
import nextstep.courses.domain.coverimage.CoverImages;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.Price;

public class PaidLecture extends BaseTime implements Lecture {

  private final LectureType lectureType = LectureType.PAID;
  private final Long id;
  private final String title;
  private final CoverImages coverImages = new CoverImages();
  private final LectureStatus lectureStatus;
  private final LectureRecruitingStatus lectureRecruitingStatus;
  private final RegistrationPeriod registrationPeriod;
  private final Price price;
  private final Integer limitStudentCount;
  private final Students students = Students.defaultOf();
  ; // 강의 기본정보와는 다름


  public PaidLecture(Long id, String title, CoverImages coverImages,
      LectureRecruitingStatus lectureRecruitingStatus, LectureStatus lectureStatus,
      RegistrationPeriod registrationPeriod, Price price, Integer limitStudentCount,
      LocalDateTime createdAt, LocalDateTime updatedAt) {
    super(createdAt, updatedAt);
    this.id = id;
    this.title = title;
    this.coverImages.addAll(coverImages);
    this.lectureStatus = lectureStatus;
    this.lectureRecruitingStatus = lectureRecruitingStatus;
    this.registrationPeriod = registrationPeriod;
    this.price = price;
    this.limitStudentCount = limitStudentCount;
  }

  public PaidLecture(Long id, String title, CoverImages coverImages,
      LectureRecruitingStatus lectureRecruitingStatus,
      RegistrationPeriod registrationPeriod, Price price, Integer limitStudentCount) {
    this(id, title, coverImages, LectureStatus.YET, lectureRecruitingStatus, registrationPeriod,
        price, limitStudentCount, LocalDateTime.now(), null);
  }

  public PaidLecture(Long id, String title, CoverImages coverImages, LectureStatus lectureStatus,
      LectureRecruitingStatus lectureRecruitingStatus,
      RegistrationPeriod registrationPeriod, Price price, Integer limitStudentCount
      , LocalDateTime createdAt
      , LocalDateTime updatedAt) {
    this(id, title, coverImages, lectureRecruitingStatus, LectureStatus.YET, registrationPeriod,
        price, limitStudentCount, createdAt, updatedAt);
  }

  public PaidLecture(LectureEntity lecture) {
    super(lecture.getCreatedAt(), lecture.getUpdatedAt());
    this.id = lecture.id();
    this.title = lecture.title();
    this.coverImages.addAll(lecture.coverImage());
    this.lectureStatus = lecture.lectureStatus();
    this.lectureRecruitingStatus = lecture.lectureRecruitingStatus();
    this.registrationPeriod = lecture.registrationPeriod();
    this.price = lecture.price();
    this.limitStudentCount = lecture.limitStudentCount();
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

    if (this.lectureStatus == LectureStatus.DONE) {
      throw new IllegalArgumentException("이미 종료된 강의입니다.");
    }

    if (!selectedStudents.contain(nsUser)) {
      throw new IllegalArgumentException("선발된 인원만 수강신청이 가능합니다.");
    }
  }

  @Override
  public void enrollment(NsUser nsUser, Students selectedStudents) {
    this.canEnrollment(nsUser, selectedStudents);
    nsUser.hasPayment(price);
    this.students.addWithLimitCount(nsUser, limitStudentCount);
  }

  @Override
  public Lecture recruitingStart() {
    return new PaidLecture(this.id, this.title, this.coverImages, this.lectureStatus,
        LectureRecruitingStatus.RECRUITING, this.registrationPeriod, this.price,
        this.limitStudentCount, this.getCreatedAt(), this.getUpdatedAt());
  }

  @Override
  public Lecture start() {
    return new PaidLecture(this.id, this.title, this.coverImages, LectureStatus.DOING,
        LectureRecruitingStatus.RECRUITING, this.registrationPeriod, this.price,
        this.limitStudentCount, this.getCreatedAt(), this.getUpdatedAt());
  }

  @Override
  public Integer numberOfStudent() {
    return students.size();
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

  public CoverImages getCoverImage() {
    return this.coverImages;
  }

  public LectureRecruitingStatus getLectureStatus() {
    return lectureRecruitingStatus;
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

  public LectureEntity toEntity() {
    return new LectureEntity(
        this.id
        , this.title
        , this.coverImages
        , this.lectureType
        , this.lectureStatus
        , this.lectureRecruitingStatus
        , this.registrationPeriod
        , this.price
        , this.limitStudentCount
        , super.getCreatedAt()
        , super.getUpdatedAt()
    );
  }
}

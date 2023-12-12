package nextstep.courses.domain.lectures;

import java.time.LocalDateTime;
import nextstep.courses.BaseTime;
import nextstep.courses.domain.Students;
import nextstep.courses.domain.coverimage.CoverImage;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.Price;

public class PaidLecture extends BaseTime implements Lecture  {
  private final LectureType lectureType = LectureType.PAID;
  private final Long id;
  private final String title;
  private final CoverImage coverImage;
  private final LectureStatus lectureStatus;
  private final RegistrationPeriod registrationPeriod;
  private final Price price;
  private final Integer limitStudentCount;
  private final Students students = Students.defaultOf();; // 강의 기본정보와는 다름


  public PaidLecture(Long id, String title, CoverImage coverImage, LectureStatus lectureStatus,
      RegistrationPeriod registrationPeriod, Price price, Integer limitStudentCount) {
    super();
    this.id = id;
    this.title = title;
    this.coverImage = coverImage;
    this.lectureStatus = lectureStatus;
    this.registrationPeriod = registrationPeriod;
    this.price = price;
    this.limitStudentCount = limitStudentCount;
  }
  public PaidLecture(Long id, String title, CoverImage coverImage, LectureStatus lectureStatus,
      RegistrationPeriod registrationPeriod, Price price, Integer limitStudentCount
      , LectureType lectureType
      , LocalDateTime createdAt
      , LocalDateTime updatedAt) {
    super(createdAt, updatedAt);
    this.id = id;
    this.title = title;
    this.coverImage = coverImage;
    this.lectureStatus = lectureStatus;
    this.registrationPeriod = registrationPeriod;
    this.price = price;
    this.limitStudentCount = limitStudentCount;
  }

  public PaidLecture(LectureEntity lecture) {
    super(lecture.getCreatedAt(), lecture.getUpdatedAt());
    this.id = lecture.getId();
    this.title = lecture.getTitle();
    this.coverImage = lecture.getCoverImage();
    this.lectureStatus = lecture.getLectureStatus();
    this.registrationPeriod = lecture.getRegistrationPeriod();
    this.price = lecture.getPrice();
    this.limitStudentCount = lecture.getLimitStudentCount();
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
    if (!recruiting()) {
      throw new IllegalArgumentException("모집중이지 않습니다.");
    }
    nsUser.hasPayment(price);
    students.addWithLimitCount(nsUser, limitStudentCount);
  }

  @Override
  public Lecture start() {
    return new PaidLecture(this.id,this.title,this.coverImage,LectureStatus.RECRUITING, this.registrationPeriod,this.price,this.limitStudentCount);
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

  public LectureEntity toEntity() {
    return new LectureEntity(
        this.id
        , this.title
        , this.coverImage
        , this.lectureType
        , this.lectureStatus
        , this.registrationPeriod
        , this.price
        , this.limitStudentCount
        , super.getCreatedAt()
        , super.getUpdatedAt()
    );
  }
}

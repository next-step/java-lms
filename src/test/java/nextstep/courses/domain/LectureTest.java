package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import nextstep.courses.domain.coverimage.CoverImage;
import nextstep.courses.domain.coverimage.CoverImages;
import nextstep.courses.domain.lectures.FreeLecture;
import nextstep.courses.domain.lectures.Lecture;
import nextstep.courses.domain.lectures.LectureRecruitingStatus;
import nextstep.courses.domain.lectures.PaidLecture;
import nextstep.courses.domain.lectures.RegistrationPeriod;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import nextstep.users.domain.Payment;
import nextstep.users.domain.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LectureTest {

  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private CoverImages coverImage;

  @BeforeEach
  void setting() {
    startDate = LocalDateTime.of(2023, 4, 3, 11, 30);
    endDate = LocalDateTime.of(2023, 6, 3, 11, 30);
    CoverImage coverImageOne = CoverImage.defaultOf("file.png", 2000L, 900, 600);
    coverImage = new CoverImages(coverImageOne);
  }

  @Test
  @DisplayName("무료 강의 생성")
  public void free_lecture_test() {
    // given
    Lecture lecture = new FreeLecture(0L, "test", coverImage, LectureRecruitingStatus.PREPARING,
        new RegistrationPeriod(startDate, endDate));
    // when
    boolean result = lecture.isFree();
    // then
    assertThat(result).isTrue();
  }

  @Test
  @DisplayName("유료 강의 인원 제한 테스트")
  public void paid_lecture_person_limit_test() {
    // given
    int maxStudent = 0;
    Lecture lecture = new PaidLecture(0L, "test", coverImage, LectureRecruitingStatus.PREPARING,
        new RegistrationPeriod(startDate, endDate), new Price(BigDecimal.TEN), maxStudent);

    // then
    assertThrows(IllegalArgumentException.class
        , () -> lecture.enrollment(NsUserTest.SANJIGI));
  }

  @Test
  @DisplayName("강의 수강 신청 테스트")
  public void lecture_enrolment() {
    // given
    int maxStudent = 1;
    Lecture lecture = new PaidLecture(0L, "test", coverImage, LectureRecruitingStatus.PREPARING,
        new RegistrationPeriod(startDate, endDate), new Price(BigDecimal.TEN), maxStudent);
    NsUser javajigi = NsUserTest.JAVAJIGI;
    javajigi.payment(new Payment(new Price(BigDecimal.TEN)));

    // when
    Lecture newLecture = lecture.recruitingStart();
    newLecture.enrollment(javajigi);

    // then
    assertThat(newLecture.numberOfStudent()).isEqualTo(1);
  }

  @Test
  @DisplayName("모집중이지 않은 강의 수강신청 테스트")
  public void lecture_enrolment_status() {
    // given
    int maxStudent = 1;
    Lecture lecture = new PaidLecture(0L, "test", coverImage, LectureRecruitingStatus.PREPARING,
        new RegistrationPeriod(startDate, endDate), new Price(BigDecimal.TEN), maxStudent);

    // then
    assertThrows(IllegalArgumentException.class
        , () -> lecture.enrollment(NsUserTest.SANJIGI));
  }

  @Test
  @DisplayName("진행 중인 강의 수강신청 테스트")
  public void lecture_enrolment_status_by_doing() {
    // given
    int maxStudent = 1;
    Lecture lecture = new PaidLecture(0L, "test", coverImage, LectureRecruitingStatus.PREPARING,
        new RegistrationPeriod(startDate, endDate), new Price(BigDecimal.TEN), maxStudent);

    // then
    assertThrows(IllegalArgumentException.class
        , () -> lecture.enrollment(NsUserTest.SANJIGI));
  }

  @Test
  @DisplayName("수강신청에서 결제가 다를 경우")
  public void lecture_enrolment_different_fail() {
    int maxStudent = 1;
    Lecture lecture = new PaidLecture(0L, "test", coverImage, LectureRecruitingStatus.PREPARING,
        new RegistrationPeriod(startDate, endDate), new Price(BigDecimal.TEN), maxStudent);
    NsUser javajigi = NsUserTest.JAVAJIGI;
    javajigi.payment(new Payment(new Price(BigDecimal.ONE)));
    Lecture startLecture = lecture.recruitingStart();

    assertThrows(IllegalArgumentException.class
        , () -> startLecture.enrollment(javajigi));
  }
}

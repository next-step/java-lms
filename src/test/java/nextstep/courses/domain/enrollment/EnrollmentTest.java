package nextstep.courses.domain.enrollment;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.Generation;
import nextstep.courses.domain.session.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import static nextstep.courses.domain.enrollment.Enrollment.REGISTER_IS_ONLY_POSSIBLE_IN_PROGRESS_STATUS;
import static nextstep.courses.domain.enrollment.Enrollment.SESSION_AMOUNT_IS_NOT_CORRECT;
import static nextstep.courses.domain.session.SessionEnrollmentInfo.STUDENT_COUNT_IS_FULL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EnrollmentTest {

  private static Course COURSE;

  @BeforeEach
  public void beforeEach() {
    init();
  }

  @Test
  @DisplayName("무료 강의 신청 한 경우" +
      "Enrollment 생성 테스트")
  void free_enrollment_create_test() {
    Optional<Session> freeSession = COURSE.getSessions().stream()
        .filter(it -> it.isFree() && it.checkRegisterPossibleStatus())
        .findFirst();
    Enrollment enrollment = Enrollment.register(1L, 1004L, COURSE.getId(), freeSession.get(), 0L);

    assertThat(enrollment.getId()).isEqualTo(1L);
    assertThat(enrollment.getUserId()).isEqualTo(1004L);
    assertThat(enrollment.getCourseId()).isEqualTo(COURSE.getId());
  }

  @Test
  @DisplayName("유료 강의 신청 한 경우" +
      "Enrollment 생성 테스트")
  void not_free_enrollment_create링_test() {
    Optional<Session> notFreeSession = COURSE.getSessions().stream()
        .filter(it -> !it.isFree() && it.checkRegisterPossibleStatus())
        .findFirst();
    Enrollment enrollment = Enrollment.register(1L, 1004L, COURSE.getId(), notFreeSession.get(), notFreeSession.get().getSessionAmount());

    assertThat(enrollment.getId()).isEqualTo(1L);
    assertThat(enrollment.getUserId()).isEqualTo(1004L);
    assertThat(enrollment.getCourseId()).isEqualTo(COURSE.getId());
  }

  @Test
  @DisplayName("강의 상태가 모집 중이 아닌 경우" +
      "exception 테스트")
  void enrollment_fail_case_test_by_not_possible_register_status() {
    Optional<Session> notInProgressSession = COURSE.getSessions().stream()
        .filter(it -> !it.checkRegisterPossibleStatus())
        .findFirst();

    assertThatThrownBy(() -> Enrollment.register(1L, 1004L, COURSE.getId(), notInProgressSession.get(), 0L))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(String.format(REGISTER_IS_ONLY_POSSIBLE_IN_PROGRESS_STATUS, notInProgressSession.get().getId(), notInProgressSession.get().getSessionStatus()));
  }

  @Test
  @DisplayName("수강료가 일치하지 않는 경우" +
      "exception 테스트")
  void enrollment_fail_case_test_by_amount_not_correct() {
    Optional<Session> notFreeSession = COURSE.getSessions().stream()
        .filter(it -> !it.isFree() && it.checkRegisterPossibleStatus())
        .findFirst();

    assertThatThrownBy(() -> Enrollment.register(1L, 1004L, COURSE.getId(), notFreeSession.get(), 0L))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(String.format(SESSION_AMOUNT_IS_NOT_CORRECT, 0L, notFreeSession.get().getSessionAmount()));
  }

  @Test
  @DisplayName("수강 인원이 꽉 찬 경우 경우" +
      "exception 테스트")
  void enrollment_fail_case_test_by_student_count_is_full() {
    Optional<Session> notFreeSession = COURSE.getSessions().stream()
        .filter(it -> !it.isFree() && it.checkRegisterPossibleStatus())
        .findFirst();

    Enrollment.register(1L, 1004L, COURSE.getId(), notFreeSession.get(), notFreeSession.get().getSessionAmount());
    assertThatThrownBy(() -> Enrollment.register(1L, 1004L, COURSE.getId(), notFreeSession.get(), notFreeSession.get().getSessionAmount()))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(STUDENT_COUNT_IS_FULL);
  }

  private static void init() {
    COURSE = new Course(1L, new Generation(1L), "TDD, 클린 코드 with Java", 100L, LocalDateTime.now(), LocalDateTime.now());

    Session session1 = new Session(
        1L,
        new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusMonths(1L)),
        new SessionInfo("자동차 경주", SessionStatus.IN_PROGRESS, true, 0L, null),
        new CoverImage("자동차 경주 이미지 커버", ImageType.PNG, new CoverImageMeta(1024, 300, 200))
    );

    Session session2 = new Session(
        2L,
        new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusMonths(1L)),
        new SessionInfo("수강신청", SessionStatus.IN_PROGRESS, false, 50000L, 1),
        new CoverImage("수강신청 이미지 커버", ImageType.PNG, new CoverImageMeta(1024, 300, 200))
    );

    Session session3 = new Session(
        3L,
        new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusMonths(1L)),
        new SessionInfo("수강신청 실패 케이스", SessionStatus.END, false, 50000L, 1),
        new CoverImage("수강신청 이미지 커버", ImageType.PNG, new CoverImageMeta(1024, 300, 200))
    );

    COURSE.addSessions(Set.of(session1, session2, session3));
  }
}

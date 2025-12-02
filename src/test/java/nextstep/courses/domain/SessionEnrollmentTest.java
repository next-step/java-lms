package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class SessionEnrollmentTest {

  @Test
  void 유료강의_최대수강인원_초과하면_예외() {
    SessionEnrollment enrollment = new SessionEnrollment(300, 1000, 300);

    assertThatThrownBy(() -> enrollment.enroll(1000))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("최대 수강 인원을 초과할 수 없습니다.");
  }

  @Test
  void 유료강의_결제금액과_수강료가_동일하지_않으면_예외() {
    SessionEnrollment enrollment = SessionEnrollment.paid(300, 1000);

    assertThatThrownBy(() -> enrollment.enroll(999))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("수강료와 지불한 금액이 정확히 일치해야 합니다.");
  }

  @Test
  void 유료강의_최대수강인원이하_결제금액과수강료동일() {
    SessionEnrollment enrollment = SessionEnrollment.paid(300, 1000);

    assertThatCode(() -> enrollment.enroll(1000))
        .doesNotThrowAnyException();
  }

  @Test
  void 무료강의_최대수강인원제한_없음() {
    SessionEnrollment enrollment = SessionEnrollment.free();

    for (int i = 0; i < 1000; i++) {
      enrollment = enrollment.enroll();
    }
  }

  @Test
  void 무료강의_수강료_0원() {
    SessionEnrollment enrollment = SessionEnrollment.free();

    assertThatCode(() -> enrollment.enroll(0))
        .doesNotThrowAnyException();
  }
}
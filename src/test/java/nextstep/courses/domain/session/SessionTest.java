package nextstep.courses.domain.session;

import static nextstep.courses.domain.session.SessionBuilder.aSession;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

class SessionTest {

  @Test
  void 모집중일때_수강신청_가능() {
    Session session = aSession().recruiting().build();

    assertDoesNotThrow(() -> session.enroll(0));
  }

  @Test
  void 모집중이_아닐때_수강신청하면_예외() {
    Session session = aSession().build();

    assertThatThrownBy(() -> session.enroll(0))
        .isInstanceOf(IllegalStateException.class)
        .hasMessage("모집중인 강의만 수강신청이 가능합니다.");
  }

  @Test
  void 준비중이_아닐때_모집시작하면_예외() {
    Session session = aSession().recruiting().build();

    assertThatThrownBy(() -> session.open())
        .isInstanceOf(IllegalStateException.class)
        .hasMessage("준비중인 강의만 모집을 시작할 수 있습니다.");
  }

  @Test
  void 모집중이_아닐때_종료하면_예외() {
    Session session = aSession().build();

    assertThatThrownBy(() -> session.close())
        .isInstanceOf(IllegalStateException.class)
        .hasMessage("모집중인 강의만 종료할 수 있습니다.");
  }
}
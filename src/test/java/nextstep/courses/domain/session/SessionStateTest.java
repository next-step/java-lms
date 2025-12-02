package nextstep.courses.domain.session;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class SessionStateTest {

  @Test
  void 준비중에서_모집시작_가능() {
    assertThat(SessionState.PREPARING.open()).isEqualTo(SessionState.RECRUITING);
  }

  @Test
  void 모집중에서_종료_가능() {
    assertThat(SessionState.RECRUITING.close()).isEqualTo(SessionState.CLOSED);
  }

  @Test
  void 준비중이_아닐때_모집시작하면_예외() {
    assertThatThrownBy(() -> SessionState.RECRUITING.open())
        .isInstanceOf(IllegalStateException.class)
        .hasMessage("준비중인 강의만 모집을 시작할 수 있습니다.");

    assertThatThrownBy(() -> SessionState.CLOSED.open())
        .isInstanceOf(IllegalStateException.class)
        .hasMessage("준비중인 강의만 모집을 시작할 수 있습니다.");
  }

  @Test
  void 모집중이_아닐때_종료하면_예외() {
    assertThatThrownBy(() -> SessionState.PREPARING.close())
        .isInstanceOf(IllegalStateException.class)
        .hasMessage("모집중인 강의만 종료할 수 있습니다.");

    assertThatThrownBy(() -> SessionState.CLOSED.close())
        .isInstanceOf(IllegalStateException.class)
        .hasMessage("모집중인 강의만 종료할 수 있습니다.");
  }

  @Test
  void 모집중일때만_수강신청_가능() {
    assertThat(SessionState.PREPARING.canEnroll()).isFalse();
    assertThat(SessionState.RECRUITING.canEnroll()).isTrue();
    assertThat(SessionState.CLOSED.canEnroll()).isFalse();
  }
}
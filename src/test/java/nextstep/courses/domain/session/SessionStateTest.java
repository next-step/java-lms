package nextstep.courses.domain.session;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class SessionStateTest {

  @Test
  void 준비중_다음_상태는_모집중() {
    assertThat(SessionState.PREPARING.next()).isEqualTo(SessionState.RECRUITING);
  }

  @Test
  void 모집중_다음_상태는_종료() {
    assertThat(SessionState.RECRUITING.next()).isEqualTo(SessionState.CLOSED);
  }

  @Test
  void 종료_상태에서_다음_상태로_변경하면_예외() {
    assertThatThrownBy(() -> SessionState.CLOSED.next())
        .isInstanceOf(IllegalStateException.class)
        .hasMessage("종료된 강의는 상태를 변경할 수 없습니다.");
  }

  @Test
  void 모집중일때만_수강신청_가능() {
    assertThat(SessionState.PREPARING.canEnroll()).isFalse();
    assertThat(SessionState.RECRUITING.canEnroll()).isTrue();
    assertThat(SessionState.CLOSED.canEnroll()).isFalse();
  }
}
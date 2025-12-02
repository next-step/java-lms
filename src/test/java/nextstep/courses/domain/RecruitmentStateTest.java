package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class RecruitmentStateTest {

  @Test
  void 준비중_다음_상태는_모집중() {
    assertThat(RecruitmentState.PREPARING.next()).isEqualTo(RecruitmentState.RECRUITING);
  }

  @Test
  void 모집중_다음_상태는_종료() {
    assertThat(RecruitmentState.RECRUITING.next()).isEqualTo(RecruitmentState.CLOSED);
  }

  @Test
  void 종료_상태에서_다음_상태로_변경하면_예외() {
    assertThatThrownBy(() -> RecruitmentState.CLOSED.next())
        .isInstanceOf(IllegalStateException.class)
        .hasMessage("종료된 강의는 상태를 변경할 수 없습니다.");
  }

  @Test
  void 모집중일때만_수강신청_가능() {
    assertThat(RecruitmentState.PREPARING.canEnroll()).isFalse();
    assertThat(RecruitmentState.RECRUITING.canEnroll()).isTrue();
    assertThat(RecruitmentState.CLOSED.canEnroll()).isFalse();
  }
}
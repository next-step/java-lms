package nextstep.courses.domain.session;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class SessionProgressStateTest {

    @Test
    void 준비중에서_진행중으로_전환() {
        assertThat(SessionProgressState.PREPARING.start()).isEqualTo(SessionProgressState.IN_PROGRESS);
    }

    @Test
    void 진행중에서_종료로_전환() {
        assertThat(SessionProgressState.IN_PROGRESS.finish()).isEqualTo(SessionProgressState.FINISHED);
    }

    @Test
    void 준비중이_아닐때_시작하면_예외() {
        assertThatThrownBy(() -> SessionProgressState.IN_PROGRESS.start())
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("준비중인 강의만 시작할 수 있습니다.");

        assertThatThrownBy(() -> SessionProgressState.FINISHED.start())
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("준비중인 강의만 시작할 수 있습니다.");
    }

    @Test
    void 진행중이_아닐때_종료하면_예외() {
        assertThatThrownBy(() -> SessionProgressState.PREPARING.finish())
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("진행중인 강의만 종료할 수 있습니다.");

        assertThatThrownBy(() -> SessionProgressState.FINISHED.finish())
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("진행중인 강의만 종료할 수 있습니다.");
    }

    @Test
    void 준비중과_진행중일때만_Enrollment_생성_가능() {
        assertThat(SessionProgressState.PREPARING.canCreateEnrollment()).isTrue();
        assertThat(SessionProgressState.IN_PROGRESS.canCreateEnrollment()).isTrue();
        assertThat(SessionProgressState.FINISHED.canCreateEnrollment()).isFalse();
    }
}
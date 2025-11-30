package nextstep.courses.domain.session;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionStatusTest {

    @Test
    public void 준비중_상태일때_수강신청_불가() {
        SessionStatus status = SessionStatus.PREPARING;

        assertThat(status.canEnroll()).isFalse();
    }

    @Test
    public void 모집중_상태일때_수강신청_가능() {
        SessionStatus status = SessionStatus.RECRUITING;

        assertThat(status.canEnroll()).isTrue();
    }

    @Test
    public void 종료_상태일때_수강신청_불가() {
        SessionStatus status = SessionStatus.CLOSED;

        assertThat(status.canEnroll()).isFalse();
    }


    @Test
    public void 문자열로_상태_생성() {
        assertThat(SessionStatus.from("준비중")).isEqualTo(SessionStatus.PREPARING);
        assertThat(SessionStatus.from("모집중")).isEqualTo(SessionStatus.RECRUITING);
        assertThat(SessionStatus.from("종료")).isEqualTo(SessionStatus.CLOSED);
    }

    @Test
    public void 잘못된_상태_문자열이면_예외() {
        assertThatThrownBy(() -> SessionStatus.from("진행중"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된 강의 상태");
    }
}

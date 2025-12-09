package nextstep.courses.domain.session;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ProgressStatusTest {
    @Test
    void 각상태별_생성확인() {
        assertThat(ProgressStatus.PREPARING.getValue()).isEqualTo("준비중");
        assertThat(ProgressStatus.IN_PROGRESS.getValue()).isEqualTo("진행중");
        assertThat(ProgressStatus.CLOSED.getValue()).isEqualTo("종료");
    }

    @Test
    void 잘못된_상태_문자열이면_예외() {
        assertThatThrownBy(() -> {
            ProgressStatus.from("모집중");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된 진행 상태");
    }
}

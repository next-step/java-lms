package nextstep.courses.domain.enums;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ProgressStateTest {
    @Test
    void 동치비교() {
        assertThat(ProgressState.of(9)).isEqualTo(ProgressState.END);
    }

    @Test
    void 미존재값조회() {
        assertThatThrownBy(() -> {
            ProgressState progressState = ProgressState.of(10);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid ProgressState value: 10");
    }

    @Test
    void isEnd() {
        assertThat(ProgressState.END.isEnd()).isTrue();
        assertThat(ProgressState.PROCEEDING.isEnd()).isFalse();
    }
}

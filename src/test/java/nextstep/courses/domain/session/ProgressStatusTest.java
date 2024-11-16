package nextstep.courses.domain.session;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProgressStatusTest {

    @DisplayName("진행중 상태 여부를 알 수 있다.")
    @Test
    void isNotInProgressTest() {
        assertThat(ProgressStatus.IN_PROGRESS.isNotInProgress()).isFalse();
        assertThat(ProgressStatus.PREPARE.isNotInProgress()).isTrue();
        assertThat(ProgressStatus.CLOSED.isNotInProgress()).isTrue();
    }
}
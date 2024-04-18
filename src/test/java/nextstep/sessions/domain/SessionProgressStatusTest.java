package nextstep.sessions.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SessionProgressStatusTest {

    @Test
    void 진행_상태가_END인_경우_true를_반환한다() {
        assertThat(SessionProgressStatus.END.isEnd()).isTrue();
    }
}

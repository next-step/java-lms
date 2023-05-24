package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SessionStatusTest {

    @Test
    void 강의상태가_준비중이다() {
        // given
        SessionStatus status = SessionStatus.READY;

        // when
        boolean result = status.isReady();

        // then
        assertThat(result).isTrue();
    }
}

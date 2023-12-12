package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SessionStatusTest {
    @Test
    @DisplayName("SessionStatus 생성")
    void create() {
        assertThat(SessionStatus.READY).isInstanceOf(SessionStatus.class);
    }
}

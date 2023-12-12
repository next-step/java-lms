package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SessionTest {
    @Test
    @DisplayName("강의 생성")
    void create() {
        assertThat(new Session(LocalDateTime.now(), LocalDateTime.now().plusMonths(1L)))
                .isInstanceOf(Session.class);
    }
}

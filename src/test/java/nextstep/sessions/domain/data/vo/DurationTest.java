package nextstep.sessions.domain.data.vo;

import java.time.LocalDateTime;

import nextstep.sessions.domain.data.session.Duration;
import nextstep.sessions.domain.exception.SessionsException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class DurationTest {

    @Test
    void 시작일이_종료일보다_늦음() {
        Assertions.assertThatThrownBy(() -> new Duration(LocalDateTime.now().plusHours(1), LocalDateTime.now()))
            .isInstanceOf(SessionsException.class)
            .hasMessage("시작일은 종료일보다 빨라야 합니다.");
    }
}

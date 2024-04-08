package nextstep.sessions.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.sessions.domain.SessionStatus.*;
import static nextstep.sessions.domain.SessionType.PAID;

public class SessionDetailsTest {

    @DisplayName("유료강의는 수강신청을 했을 때, 최대 수강 인원을 초과하면 예외를 반환한다")
    @Test
    void name() {
        SessionDetails details = new SessionDetails(40, 40, 30000, PAID, RECRUITING);

        Assertions.assertThatThrownBy(details::register)
                .isInstanceOf(IllegalArgumentException.class);
    }
}

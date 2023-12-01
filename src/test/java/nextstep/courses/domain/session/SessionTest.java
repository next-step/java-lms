package nextstep.courses.domain.session;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionPeriod;
import nextstep.courses.type.SessionStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionTest {

    @DisplayName("강의 생성")
    @Test
    void 강의생성() {
        // given
        SessionPeriod sessionPeriod = new SessionPeriod("2023-01-01", "2023-12-31");
        Session session = new Session("TDD", sessionPeriod, true, 10000, 10, SessionStatus.PREPAR);
        // when
        // then
        assertThat(session.title()).isEqualTo("TDD");
    }
}

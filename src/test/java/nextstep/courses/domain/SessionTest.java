package nextstep.courses.domain;

import nextstep.courses.type.SessionStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionTest {

    @DisplayName("강의 생성")
    @Test
    void 강의생성() {
        // given
        LocalDateTime startDateTime = LocalDateTime.of(2021, 8, 1, 0, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2021, 8, 31, 23, 59);
        SessionPeriod sessionPeriod = new SessionPeriod(startDateTime, endDateTime);
        Session session = new Session("TDD", sessionPeriod, true, 10000, 10, SessionStatus.PREPAR);
        // when
        // then
        assertThat(session.title()).isEqualTo("TDD");
    }
}

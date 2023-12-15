package nextstep.sessions.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SessionsTest {

    Sessions sessions;

    Session sessionA;
    Session sessionB;
    Session sessionC;

    @BeforeEach
    void setUp() {
        sessionA = new Session(1L, "test1", SessionStatus.RECRUITING, new FreeSessionType(), LocalDate.now(), LocalDate.now());
        sessionB = new Session(2L, "test2", SessionStatus.TERMINATION, new FreeSessionType(), LocalDate.now(), LocalDate.now());
        sessionC = new Session(3L, "test3", SessionStatus.RECRUITING, new FreeSessionType(), LocalDate.now(), LocalDate.now());

        sessions = new Sessions(List.of(sessionA, sessionB, sessionC));
    }

    @Test
    void 수강_신청_가능한_강의_목록_조회_테스트() {
        assertThat(sessions.getPossibleSessionList()).hasSize(2);
    }
}

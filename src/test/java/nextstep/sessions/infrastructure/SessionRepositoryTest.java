package nextstep.sessions.infrastructure;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import nextstep.sessions.domain.data.Session;
import nextstep.sessions.domain.data.type.PaidType;
import nextstep.sessions.domain.data.type.SessionState;
import nextstep.sessions.domain.data.vo.*;
import nextstep.sessions.repository.SessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class SessionRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @Test
    void 강의_생성_및_조회() {
        EnrollmentInfo enrollmentInfo = new EnrollmentInfo(new SessionType(PaidType.PAID, 800000L, 10), SessionState.RECRUITING);
        OpenInfo openInfo = new OpenInfo(new Duration(LocalDateTime.now(), LocalDateTime.now().plusDays(10)));
        SessionInfo sessionInfo = new SessionInfo(enrollmentInfo, openInfo);
        Session session = new Session(sessionInfo);

        int count = sessionRepository.saveSession(session);
        assertThat(count).isEqualTo(1);

        Session savedSession = sessionRepository.findSessionBySessionId(1);
        assertThat(savedSession.sessionInfo().enrollmentInfo().sessionState()).isEqualTo(session.sessionInfo().enrollmentInfo().sessionState());
    }
}

package nextstep.courses.infrastructure;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.registration.SessionStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static nextstep.Fixtures.aSession;
import static nextstep.Fixtures.aSessionRegistrationBuilder;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class SessionRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;
    private Session session;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
        session = aSession().build();
    }

    @DisplayName("Session 저장")
    @Test
    void save() {
        long sessionId = sessionRepository.save(session);
        Session savedSession = aSession().withId(sessionId).build();
        assertThat(savedSession.getId()).isEqualTo(sessionId);
    }

    @DisplayName("Session 조회")
    @Test
    void findById() {
        long sessionId = sessionRepository.save(session);

        Session foundSession = sessionRepository.findById(sessionId);
        assertThat(foundSession).isNotNull();
        assertThat(foundSession.getId()).isEqualTo(sessionId);
    }

    @DisplayName("강의 진행상태 모집중->진행중 마이그레이션")
    @Test
    void updateSessionStatus() {
        Session session = aSession().withId(1L)
                .withSessionRegistration(aSessionRegistrationBuilder()
                        .withSessionStatus(SessionStatus.RECRUITING)
                        .build())
                .build();

        long sessionId = sessionRepository.save(session);

        assertThat(session.getSessionStatus().isRecruiting()).isTrue();

        Session tobeSession = session.migrationStatus();
        sessionRepository.updateSessionStatus(tobeSession);
        Session foundSession = sessionRepository.findById(sessionId);

        assertThat(foundSession.getSessionStatus()).isEqualTo(SessionStatus.PROGRESSING);

    }

}

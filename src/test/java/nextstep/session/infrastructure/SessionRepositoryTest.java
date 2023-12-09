package nextstep.session.infrastructure;

import nextstep.session.domain.FreeSession;
import nextstep.session.domain.PaidSession;
import nextstep.session.domain.Session;
import nextstep.session.domain.SessionRepository;
import nextstep.session.domain.SessionType;
import nextstep.session.domain.fixture.SessionImageFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class SessionRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @Test
    void createFreeSession() {
        Session freeSession = new FreeSession(1L, LocalDate.now(), LocalDate.now().plusDays(1), SessionImageFixture.createSessionImage());
        Long id = sessionRepository.save(freeSession);
        Session savedSession = sessionRepository.findById(id);
        assertThat(savedSession.getSessionType()).isEqualTo(SessionType.FREE);
    }

    @Test
    void createPaidSession() {
        Session paidSession = new PaidSession(1L, LocalDate.now(), LocalDate.now().plusDays(1), SessionImageFixture.createSessionImage(), 3, 10000L);
        Long id = sessionRepository.save(paidSession);
        Session savedSession = sessionRepository.findById(id);
        assertThat(savedSession.getSessionType()).isEqualTo(SessionType.PAID);
    }

}

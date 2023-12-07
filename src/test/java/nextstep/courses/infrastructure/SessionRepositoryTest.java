package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.Image;
import nextstep.courses.domain.session.Period;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

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
    void create() {
        Session session = Session.ofPaid(Period.from(), Image.from(), 1_000L, 1L);
        int count = sessionRepository.save(session);
        assertThat(count).isEqualTo(1);
    }

    @Test
    void find() {
        Session findSession = sessionRepository.findById(1000L);
        assertThat(findSession.type()).isEqualTo("PAID");
        LOGGER.debug("saveSession: {}", findSession);
    }
}

package nextstep.courses.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import nextstep.courses.domain.image.CoverImage;
import nextstep.courses.domain.policy.FreeSessionPolicy;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionPeriod;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.SessionStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
public class SessionRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;
    private SessionPeriod sessionPeriod;
    private CoverImage coverImage;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
        sessionPeriod = new SessionPeriod(LocalDate.now(), LocalDate.now().plusMonths(3));
        coverImage = new CoverImage("파일이름.png", 1024, 1500, 1000);
    }

    @Test
    void crud() {
        Session session = new Session(sessionPeriod, coverImage, new FreeSessionPolicy(), SessionStatus.RECRUITING);
        int count = sessionRepository.save(session);
        assertThat(count).isEqualTo(1);
        Session savedSession = sessionRepository.findById(1L);
        assertThat(session.status()).isEqualTo(savedSession.status());
        LOGGER.debug("Session: {}", savedSession);
    }
}

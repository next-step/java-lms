package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.*;
import nextstep.courses.repository.SessionRepository;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class SessionRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @Test
    void createAndFindTest() {
        Session newSession = new Session(
                0L,
                "ATDD",
                1L,
                8L,
                new SessionPeriod(LocalDate.of(2023, 4, 1), LocalDate.of(2023, 6, 1)),
                new CoverImage("test_url"),
                new Students(100, SessionFeeType.PAY, SessionStatus.PREPARING),
                NsUserTest.JAVAJIGI.getId(),
                LocalDateTime.of(2023, 6, 1, 0, 0, 0),
                null
        );
        int count = sessionRepository.create(newSession);
        assertThat(count).isEqualTo(1);
        Session saved = sessionRepository.findById(1L);
        assertThat(saved.getTitle()).isEqualTo(newSession.getTitle());
        LOGGER.debug("New Session = {}", newSession);
        LOGGER.debug("Saved Session = {}", saved);
    }

}
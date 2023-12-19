package nextstep.sessions.infrastructure;

import nextstep.common.Period;
import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionCharge;
import nextstep.sessions.domain.SessionImage;
import nextstep.sessions.domain.SessionImages;
import nextstep.sessions.domain.SessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.List;

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

    @DisplayName("생성한 강의를 저장하고, 그 강의를 가져올 수 있다.")
    @Test
    void crud() {
        Session session = new Session(
                "강의",
                new Period(LocalDate.of(2023, 12, 1), LocalDate.of(2023, 12, 31)),
                new SessionImages(List.of(new SessionImage(100, 300, 200, "PNG"), new SessionImage(300, 300, 200, "JPG"))),
                new SessionCharge(true, 1000, 3));
        long count = sessionRepository.save(session);
        assertThat(count).isEqualTo(2);

        Session savedSession = sessionRepository.findById(2L);
        assertThat(session.getName()).isEqualTo(savedSession.getName());
        LOGGER.debug("Session: {}", savedSession);
    }
}

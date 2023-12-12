package nextstep.sessions.infrastructure;

import nextstep.common.Period;
import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionCharge;
import nextstep.sessions.domain.SessionImage;
import nextstep.sessions.domain.SessionStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

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
                new Period(LocalDate.of(2023, 12, 1), LocalDate.of(2023, 12, 15)),
                new SessionImage(100, 300, 200, "PNG"),
                new SessionCharge(true, 1000, 3),
                SessionStatus.RECRUITING);
        long count = sessionRepository.save(session);
        assertThat(count).isEqualTo(2);

        Session savedSession = sessionRepository.findById(2L);
        assertThat(session.getName()).isEqualTo(savedSession.getName());
        LOGGER.debug("Session: {}", savedSession);
    }

    @DisplayName("생성된 강의를 가져와 변경된 데이터를 저장할 수 있다.")
    @Test
    void updateTest() {
        Session savedSession = sessionRepository.findById(1L);
        savedSession.enroll();
        int count = sessionRepository.enroll(savedSession);
        assertThat(count).isEqualTo(1);

        Session updateSession = sessionRepository.findById(1L);
        assertThat(updateSession.getStudentCount()).isEqualTo(2);
    }
}

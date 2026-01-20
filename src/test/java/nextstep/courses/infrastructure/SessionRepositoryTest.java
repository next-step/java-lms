package nextstep.courses.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.session.EnrollmentPolicy;
import nextstep.courses.domain.session.Period;
import nextstep.courses.domain.session.Session;
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

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }
    
    @Test
    void 강의를_저장할_수_있다() {
        Period period = new Period(LocalDate.of(2025, 11, 18), LocalDate.of(2025, 12, 18));
        Session session = new Session(1L, period, SessionStatus.RECRUITING, EnrollmentPolicy.free());

        Long savedId = sessionRepository.save(session);

        assertThat(savedId).isNotNull();
    }

    @Test
    void 강의를_ID로_조회할_수_있다() {
        Period period = new Period(LocalDate.of(2025, 11, 18), LocalDate.of(2025, 12, 18));
        Session session = new Session(1L, period, SessionStatus.RECRUITING, EnrollmentPolicy.free());
        Long savedId = sessionRepository.save(session);

        Session savedSession = sessionRepository.findById(savedId);

        assertThat(savedSession.getCourseId()).isEqualTo(1L);
        assertThat(savedSession.getSessionStatus()).isEqualTo(SessionStatus.RECRUITING);
    }
    
    @Test
    void 과정_ID로_강의_목록을_조회할_수_있다() {
        Period period = new Period(LocalDate.of(2025, 11, 18), LocalDate.of(2025, 12, 18));
        Session session = new Session(1L, period, SessionStatus.RECRUITING, EnrollmentPolicy.free());
        sessionRepository.save(session);

        List<Session> sessions = sessionRepository.findByCourseId(1L);

        assertThat(sessions).hasSize(1);
    }
}

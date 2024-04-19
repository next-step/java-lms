package nextstep.session.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import nextstep.session.domain.Session;
import nextstep.session.domain.SessionRepository;
import nextstep.session.domain.SessionSchedule;
import nextstep.session.domain.SessionStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

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
    void 저장() {
        Session session = Session.createPaidSession(1L, "객체지향강의", new SessionSchedule(
                LocalDate.of(2024, 6, 1),
                LocalDate.of(2024, 12, 31)),
            SessionStatus.RECRUITING, 500, 50000);

        int count = sessionRepository.save(session);
        assertThat(count).isEqualTo(1);
    }

    @Test
    void 조회() {
        Session session = Session.createPaidSession(2L, "객체지향강의", new SessionSchedule(
                LocalDate.of(2024, 6, 1),
                LocalDate.of(2024, 12, 31)),
            SessionStatus.RECRUITING, 500, 50000);

        sessionRepository.save(session);
        Session savedSession = sessionRepository.findById(1L);
        assertThat(savedSession.getTitle()).isEqualTo(session.getTitle());

    }
}

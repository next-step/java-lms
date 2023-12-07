package nextstep.courses.infrastructure;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class SessionRepositoryTest {
    private static final LocalDateTime date1 = LocalDateTime.of(2023, 12, 1, 0, 0, 0);
    private static final LocalDateTime date2 = LocalDateTime.of(2023, 12, 10, 0, 0, 0);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        Session session = new Session(1L, date1, date2, 1L, "free", null, null, 0L, "applying");
        int save = sessionRepository.save(session);
        assertThat(save).isEqualTo(1);
        Session savedSession = sessionRepository.findById(1L);
        assertThat(session.getCourseId()).isEqualTo(savedSession.getCourseId());
    }
}
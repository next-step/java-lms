package nextstep.sessions.infrastructure;

import static nextstep.sessions.domain.SessionStatus.PREPARING;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import nextstep.courses.domain.Course;
import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionRepository;

@JdbcTest
class JdbcSessionRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @Test
    void 강의를_저장한다() {
        // given
        final Course course = new Course(1L, "TDD, 클린 코드 with Java", 1L, LocalDateTime.now(), null);
        final Session session = new Session(50, PREPARING, 800_000, LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusMonths(1), course);

        // when
        final int count = sessionRepository.save(session);

        // then
        assertThat(count).isEqualTo(1L);
    }
}

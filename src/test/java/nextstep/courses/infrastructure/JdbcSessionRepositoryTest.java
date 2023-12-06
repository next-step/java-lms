package nextstep.courses.infrastructure;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.SessionUsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import({JdbcSessionUsersRepository.class})
class JdbcSessionRepositoryTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    @Autowired
    private SessionUsersRepository sessionUsersRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(sessionUsersRepository, jdbcTemplate);
    }

    @Test
    @DisplayName("성공 - 강의를 조회한다")
    void success_find_session() {
        Session session = sessionRepository.findBy(1L).get();
        assertThat(session).isEqualTo(new Session(1L));
    }
}

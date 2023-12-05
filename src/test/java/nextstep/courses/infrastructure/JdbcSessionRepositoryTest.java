package nextstep.courses.infrastructure;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.SessionUsers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

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
    @DisplayName("성공 - 강의를 조회한다")
    void success_find_session() {
        Session session = sessionRepository.findBy(1L, new SessionUsers()).get();
        assertThat(session).isEqualTo(new Session(1L));
    }

    @Test
    @DisplayName("성공 - 강의에 참여하는 유저 수를 업데이트 한다.")
    void success_update_session_user_count() {
        sessionRepository.updateCountBy(3, 1L);
        Session session = sessionRepository.findBy(1L, new SessionUsers()).get();
        assertThat(session.userCount()).isEqualTo(3);
    }
}

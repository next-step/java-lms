package nextstep.courses.infrastructure;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionFixture;
import nextstep.courses.domain.SessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.*;

@JdbcTest
class SessionRepositoryTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @Test
    @DisplayName("Session 객체 DB 저장")
    void save() {
        Session session = SessionFixture.TDD_SESSION.session();
        int count = sessionRepository.save(session);

        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("session 레코드 조회")
    void read() {
        // given
        Session session = SessionFixture.TDD_SESSION.session();
        sessionRepository.save(session);

        // when
        Session foundSession = sessionRepository.findById(session.id());

        // then
        assertThat(foundSession).isEqualTo(session);
    }
}

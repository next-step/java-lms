package nextstep.courses.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.SessionTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcOperations;

@JdbcTest
class SessionRepositoryTest {

    @Autowired
    private JdbcOperations jdbcTemplate;

    private SessionRepository sessionRepository;

    private Session session;

    @BeforeEach
    void 초기화() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);

        session = SessionTest.TEST_SESSION_CHARGED;
    }

    @AfterEach
    void 리셋() {
        jdbcTemplate.execute("alter table session alter column id restart with 1");
    }

    @Test
    @DisplayName("세션을 저장한다.")
    void 세션_저장() {
        assertAll(
            () -> assertThat(sessionRepository.save(session)).isEqualTo(1),
            () -> assertThat(sessionRepository.findById(1L)).isEqualTo(session)
        );
    }
}
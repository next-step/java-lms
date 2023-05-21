package nextstep.courses.infrastructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static nextstep.Fixtures.aSession;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class TestJdbcSessionRepositoryTest {
    private TestJdbcSessionRepository testJdbcSessionRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        testJdbcSessionRepository = new TestJdbcSessionRepository(jdbcTemplate);
    }
    @Test
    @DisplayName("임시용")
    void test() {
        int save = testJdbcSessionRepository.save(aSession().build());
        assertThat(save).isGreaterThan(0);
    }
}

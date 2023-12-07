package nextstep.courses.infrastructure;

import nextstep.courses.domain.SessionEnroll;
import nextstep.courses.domain.SessionEnrollRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class SessionEnrollRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionEnrollRepository sessionEnrollRepository;

    @BeforeEach
    void setUp() {
        sessionEnrollRepository = new JdbcSessionEnrollRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        SessionEnroll sessionEnroll = new SessionEnroll(1L, 1L, "1L");
        int save = sessionEnrollRepository.save(sessionEnroll);
        assertThat(save).isEqualTo(1);
        SessionEnroll savedEnroll = sessionEnrollRepository.findById(1L);
        assertThat(sessionEnroll.getSessionId()).isEqualTo(savedEnroll.getSessionId());
    }
}
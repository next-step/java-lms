package nextstep.session.domain;

import nextstep.session.infrastructure.JdbcSessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

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
    void crud() {
        Session session = new Session(100L, ProgressStatus.READY, RecruitmentStatus.RECRUITING);
        int count = sessionRepository.save(session);
        assertThat(count).isEqualTo(1);

        Session savedSession = sessionRepository.findById(3L);
        assertThat(session.getMaxNumberOfStudent()).isEqualTo(savedSession.getMaxNumberOfStudent());
        assertThat(session.getProgressStatus()).isEqualTo(savedSession.getProgressStatus());
    }
}

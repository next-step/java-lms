package nextstep.courses.infrastructure;

import nextstep.courses.domain.Cost;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.*;

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
    @DisplayName("crud")
    void crud() {
        Session session = Session.of(1L, "lms1", "img://cover.com/lms", 1, Cost.FREE, State.READY, 30);
        int count = sessionRepository.save(session);
        assertThat(count).isEqualTo(1);

        Session savedSession = sessionRepository.findById(1L).orElseThrow();
        assertThat(savedSession.getTitle()).isEqualTo(session.getTitle());
    }

}

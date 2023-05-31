package nextstep.courses.infrastructure;

import nextstep.courses.domain.SessionCostType;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

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
        Session session = Session.of(1L, "lms1", "img://cover.com/lms", 1
                , SessionCostType.FREE, State.READY, 30
                , LocalDateTime.of(2023, 6, 1, 14, 0, 0)
                , LocalDateTime.of(2023, 6, 30, 14, 0, 0));
        int count = sessionRepository.save(session);
        assertThat(count).isEqualTo(1);

        Session savedSession = sessionRepository.findById(1L).orElseThrow();
        assertThat(savedSession.getTitle()).isEqualTo(session.getTitle());
    }

}

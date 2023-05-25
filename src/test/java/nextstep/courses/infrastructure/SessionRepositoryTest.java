package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class SessionRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        this.sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        Session session = new Session(1L, new SessionInfo(10, 0, SessionStatusType.PREPARE), new CoverImage("imageUrl"), new SessionDate(LocalDateTime.now(), LocalDateTime.now()), new Price(1000));
        int count = sessionRepository.save(session, 2L);
        assertThat(count).isEqualTo(1);

        Session savedSession = sessionRepository.findById(1L);
        assertThat(session.getSessionInfo().getMaxStudents()).isEqualTo(savedSession.getSessionInfo().getMaxStudents());

        List<Session> findSessionByCourseId = sessionRepository.findByCourseId(2L);
        assertThat(findSessionByCourseId)
                .hasSize(1);
        assertThat(session.getId())
                .isEqualTo(findSessionByCourseId.get(0).getId());

        LOGGER.debug("savedSession: {}", savedSession);
        LOGGER.debug("findSessionByCourseId: {}", findSessionByCourseId);
    }
}

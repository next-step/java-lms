package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class SessionRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SessionRepository sessionRepository;

    @BeforeEach
    void setup() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Test
    void save() {
        Session session = new Session(10L, "무료강의입니다.", "test.jpg", LocalDateTime.now().plusDays(8), LocalDateTime.now().plusDays(15), 1L);
        int save = sessionRepository.save(session);
        assertThat(save).isEqualTo(1);
    }

    @Test
    void findById() {
        Session sessionBySessionId = sessionRepository.findById(1L);
        assertThat(sessionBySessionId.getId()).isEqualTo(1L);
    }

    @Test
    void findByCourseId() {
        List<Session> sessionBySessionId = sessionRepository.findByCourseId(1L);
        assertThat(sessionBySessionId.size()).isEqualTo(2);
    }

    @Test
    void update() {
        Session findSession = sessionRepository.findById(1L);
        findSession.changeToStatus(SessionType.RECRUITING);
        sessionRepository.update(findSession);

        Session session = sessionRepository.findById(1L);
        assertThat(session.getStatus()).isEqualTo(SessionType.RECRUITING);
    }

    @Test
    void delete() {
        int delete = sessionRepository.delete(1L);
        assertThat(delete).isEqualTo(1);
    }

    @Test
    void findBySessionIds() {
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);

        Optional<List<Session>> bySessionIds = sessionRepository.findBySessionIds(ids);
        assertThat(bySessionIds.orElseGet(null).size()).isEqualTo(2);

    }
}
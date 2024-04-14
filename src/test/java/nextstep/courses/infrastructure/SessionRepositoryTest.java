package nextstep.courses.infrastructure;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionType;
import nextstep.courses.infrastructure.engine.SessionRepository;
import nextstep.courses.infrastructure.entity.SessionEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

import static nextstep.courses.domain.fixture.SessionFixture.session;
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
    @DisplayName("save()")
    void save() {
        Session session = session(SessionType.FREE);
        SessionEntity entity = new SessionEntity(session, LocalDateTime.now());

        int count = sessionRepository.save(entity);

        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("findById()")
    void findById() {
        Session session = session(SessionType.FREE);
        SessionEntity entity = new SessionEntity(session, LocalDateTime.now());
        sessionRepository.save(entity);

        SessionEntity saveEntity = sessionRepository.findById(1L);

        assertThat(saveEntity.getType()).isEqualTo(SessionType.FREE.get());
    }

}

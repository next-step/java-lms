package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionType;
import nextstep.courses.infrastructure.engine.SessionCoverImageRepository;
import nextstep.courses.infrastructure.engine.SessionRepository;
import nextstep.courses.infrastructure.engine.SessionStudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static nextstep.courses.domain.fixture.SessionFixture.freeSession;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class SessionRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Mock
    private SessionCoverImageRepository sessionCoverImageRepository;

    @Mock
    private SessionStudentRepository sessionStudentRepository;

    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate,
                sessionCoverImageRepository, 
                sessionStudentRepository);
    }

    @Test
    @DisplayName("save()")
    void save() {
        Session session = freeSession();

        int count = sessionRepository.save(session);

        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("findById() Optional.isPresent()")
    void findByIdIsPresent() {
        Session session = freeSession();
        sessionRepository.save(session);

        Optional<Session> optionalSession = sessionRepository.findById(1L);

        assertThat(optionalSession).isPresent();
        assertThat(optionalSession.get().getType()).isEqualTo(SessionType.FREE);
    }

    @Test
    @DisplayName("findById() Optional.isEmpty()")
    void findByIdIsEmpty() {
        Session session = freeSession();
        sessionRepository.save(session);

        Optional<Session> optionalSession = sessionRepository.findById(2L);
        assertThat(optionalSession).isEmpty();
    }

}

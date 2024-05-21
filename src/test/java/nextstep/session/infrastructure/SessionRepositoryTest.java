package nextstep.session.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import nextstep.session.domain.Session;
import nextstep.session.domain.SessionProgressStatus;
import nextstep.session.domain.SessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;

@JdbcTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class SessionRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }


    @Test
    void 저장() {
        Session session = Session.createSessionWithProgressStatusAndFee(
            SessionProgressStatus.IN_PROGRESS, 50000);
        int count = sessionRepository.save(session);
        assertThat(count).isEqualTo(1);
    }

    @Test
    void 조회() {
        Session session = Session.createSessionWithProgressStatusAndFee(
            SessionProgressStatus.IN_PROGRESS, 50000);

        sessionRepository.save(session);
        Session savedSession = sessionRepository.findById(1L);
        assertThat(savedSession.getTitle()).isEqualTo(session.getTitle());

    }
}

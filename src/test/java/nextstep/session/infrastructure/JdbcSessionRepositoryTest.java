package nextstep.session.infrastructure;

import nextstep.session.domain.Session;
import nextstep.session.domain.SessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;


import static nextstep.session.TestFixtures.registableRecrutingPaidSession;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class JdbcSessionRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    /*
    TODO
        추가 검증 필요
     */
    @Test
    void findById() {
        // given
        Session recrutingPaidSession = registableRecrutingPaidSession();
        int sessionId = sessionRepository.save(recrutingPaidSession);

        // when
        boolean empty = sessionRepository.findById((long) sessionId)
                .isEmpty();

        // then
        assertThat(empty).isFalse();
    }
}

package nextstep.session.infrastructure;

import nextstep.image.infrastructure.JdbcImageRepository;
import nextstep.session.domain.Session;
import nextstep.session.domain.SessionRepository;
import nextstep.users.infrastructure.JdbcUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@JdbcTest
class JdbcSessionRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(
                jdbcTemplate,
                new JdbcUserRepository(jdbcTemplate, namedParameterJdbcTemplate),
                new JdbcImageRepository(jdbcTemplate)
        );
    }

    @Test
    void findById() {
        Optional<Session> optionalSession = sessionRepository.findById((1L));

        assertThat(optionalSession).isPresent();
        Session session = optionalSession.get();
        assertThat(session).isNotNull();
        assertAll(
                () -> assertThat(session.getCoverImage()).isNotNull(),
                () -> assertThat(session.getMembers()).isNotNull()
        );
    }
}

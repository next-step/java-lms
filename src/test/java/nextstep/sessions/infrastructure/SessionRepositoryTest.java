package nextstep.sessions.infrastructure;

import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionRepository;
import nextstep.sessions.domain.builder.SessionBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class SessionRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @DisplayName("세션을 저장하고 조회한다")
    @Test
    void crud() {
        Session tddWithJava1 = new SessionBuilder()
                .withSessionName("tdd with java")
                .build();

        sessionRepository.save(tddWithJava1);
        Session session = sessionRepository.findById(1L)
                .orElseThrow();

        assertThat(session.getId()).isEqualTo(1L);
    }

}

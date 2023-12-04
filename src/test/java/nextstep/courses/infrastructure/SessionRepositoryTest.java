package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.*;
import nextstep.courses.type.SessionStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

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
    void 생성테스트() {
        SessionPeriod sessionPeriod = new SessionPeriod("2023-12-01", "2023-12-31");
        Price price = new Price(false, 10000, new ParticipantManager(10));
        Session session = new Session("title", sessionPeriod, price, SessionStatus.RECRUIT, null, 1L);
        int result = sessionRepository.save(session);
        assertThat(result).isOne();
    }

}

package nextstep.sessions.infrastructure;

import nextstep.sessions.domain.Price;
import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionRegisterDetails;
import nextstep.sessions.domain.SessionRegisterDetailsRepository;
import nextstep.sessions.domain.SessionRepository;
import nextstep.sessions.domain.builder.SessionBuilder;
import nextstep.sessions.domain.builder.SessionRegisterDetailsBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class SessionRegisterDetailsRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    SessionRepository sessionRepository;

    SessionRegisterDetailsRepository sessionRegisterDetailsRepository;

    private Session session;
    private SessionRegisterDetails registerDetails;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
        sessionRegisterDetailsRepository = new JdbcSessionRegisterDetailsRepository(jdbcTemplate);

        session = new SessionBuilder().withSessionName("TDD, CleanCode").build();
        sessionRepository.save(session);

        registerDetails = new SessionRegisterDetailsBuilder()
                .withPrice(new Price(30000L))
                .withSession(session)
                .build();
    }

    @DisplayName("세션 등록 상세를 저장한다")
    @Test
    void save() {
        int count = sessionRegisterDetailsRepository.save(registerDetails);
        assertThat(count).isEqualTo(1);
    }

    @DisplayName("세션 등록 상세를 조회한다")
    @Test
    void findById() {
        sessionRegisterDetailsRepository.save(registerDetails);

        SessionRegisterDetails registerDetails = sessionRegisterDetailsRepository.findById(1L)
                .orElseThrow();
        assertThat(registerDetails.getId()).isEqualTo(1L);
    }

}

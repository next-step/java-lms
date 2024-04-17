package nextstep.sessions.infrastructure;

import nextstep.sessions.domain.SessionRegisterDetails;
import nextstep.sessions.domain.SessionStatus;
import nextstep.sessions.domain.SessionType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
public class SessionRegisterDetailsRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    SessionRegisterDetailsRepository sessionRegisterDetailsRepository;

    @BeforeEach
    void setUp() {
        sessionRegisterDetailsRepository = new JdbcSessionRegisterDetailsRepository(jdbcTemplate);
    }

    @DisplayName("세션 등록 상세를 저장한다")
    @Test
    void save() {
        SessionRegisterDetails sessionRegisterDetails = new SessionRegisterDetails(20, 40, 100000L, SessionType.PAID, SessionStatus.RECRUITING);
        int count = sessionRegisterDetailsRepository.save(sessionRegisterDetails);
        Assertions.assertThat(count).isEqualTo(1);
    }

}

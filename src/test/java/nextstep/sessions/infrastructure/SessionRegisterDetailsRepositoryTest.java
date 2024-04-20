package nextstep.sessions.infrastructure;

import nextstep.sessions.domain.CountOfStudent;
import nextstep.sessions.domain.Price;
import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionRegisterDetails;
import nextstep.sessions.domain.SessionRegisterDetailsRepository;
import nextstep.sessions.domain.SessionRepository;
import nextstep.sessions.domain.SessionStatus;
import nextstep.sessions.domain.SessionType;
import nextstep.sessions.domain.builder.SessionBuilder;
import nextstep.users.domain.NsUserTest;
import nextstep.users.infrastructure.JdbcUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class SessionRegisterDetailsRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    SessionRegisterDetailsRepository sessionRegisterDetailsRepository;
    SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
        sessionRegisterDetailsRepository = new JdbcSessionRegisterDetailsRepository(jdbcTemplate, sessionRepository);

        Session tddCleanCode = new SessionBuilder()
                .withSessionName("TDD, CleanCode")
                .build();

        sessionRepository.save(tddCleanCode);
    }

    @DisplayName("세션 등록 상세를 저장한다")
    @Test
    void save() {
        SessionRegisterDetails sessionRegisterDetails = new SessionRegisterDetails(
                1L,
                1L,
                new CountOfStudent(20, 40, SessionType.PAID),
                new Price(100000L),
                SessionStatus.RECRUITING,
                List.of(NsUserTest.JAVAJIGI)
        );
        int count = sessionRegisterDetailsRepository.save(sessionRegisterDetails);
        assertThat(count).isEqualTo(1);
    }

    @DisplayName("세션 등록 상세를 조회한다")
    @Test
    void findById() {
        SessionRegisterDetails sessionRegisterDetails = new SessionRegisterDetails(1L, 1L, new CountOfStudent(20, 40, SessionType.PAID), new Price(100000L), SessionStatus.RECRUITING, List.of(NsUserTest.JAVAJIGI));
        sessionRegisterDetailsRepository.save(sessionRegisterDetails);

        SessionRegisterDetails registerDetails = sessionRegisterDetailsRepository.findById(1L)
                .orElseThrow();
        assertThat(registerDetails.getId()).isEqualTo(1L);
    }

}

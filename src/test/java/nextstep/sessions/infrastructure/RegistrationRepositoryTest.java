package nextstep.sessions.infrastructure;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.data.Session;
import nextstep.sessions.domain.data.vo.Registration;
import nextstep.sessions.repository.RegistrationRepository;
import nextstep.sessions.repository.SessionRepository;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class RegistrationRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;
    private RegistrationRepository registrationRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
        registrationRepository = new JdbcRegistrationRepository(jdbcTemplate);
    }

    @Test
    void 수강_신청_및_신청_내역_조회() {
        int sessionId = 6;
        Session session = sessionRepository.findSessionBySessionId(sessionId);
        List<Registration> registrations = registrationRepository.findAllRegistrations(sessionId);

        Registration registration = new Registration(session, NsUserTest.SANJIGI, new Payment(1L, 2L, 3L, 800000L));
        registrationRepository.saveRegistration(sessionId, registration);
        List<Registration> current_registrations = registrationRepository.findAllRegistrations(sessionId);
        assertThat(current_registrations).hasSize(registrations.size() + 1);
        assertThat(current_registrations.stream()
            .map(Registration::userId)
            .collect(Collectors.toList())
        ).contains(registration.userId());
    }

}

package nextstep.sessions.infrastructure;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.data.Session;
import nextstep.sessions.domain.data.type.ApprovalType;
import nextstep.sessions.domain.data.type.SelectionType;
import nextstep.sessions.domain.data.vo.Registration;
import nextstep.sessions.repository.RegistrationRepository;
import nextstep.sessions.repository.SessionRepository;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class NewRegistrationRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;
    private RegistrationRepository registrationRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcNewSessionRepository(jdbcTemplate);
        registrationRepository = new JdbcNewRegistrationRepository(jdbcTemplate);
    }

    @Test
    void 수강_신청_및_신청_내역_조회() {
        int sessionId = 6;
        Session session = sessionRepository.findSessionBySessionId(sessionId);
        List<Registration> registrations = registrationRepository.findAllRegistrations(sessionId);

        Registration registration = new Registration(session, NsUserTest.SANJIGI, new Payment(1L, 2L, 3L, 800000L));
        registrationRepository.saveRegistration(sessionId, registration);
        List<Registration> currentRegistrations = registrationRepository.findAllRegistrations(sessionId);
        assertThat(currentRegistrations).hasSize(registrations.size() + 1);
        assertThat(currentRegistrations.stream()
            .map(Registration::userId)
            .collect(Collectors.toList())
        ).contains(registration.userId());
    }

    @Test
    void 수강생_선발_처리() {
        int registrationId = 2;
        Registration registration = registrationRepository.findRegistrationByRegistrationId(registrationId).get();
        assertThat(registration.selectionType()).isEqualTo(SelectionType.BEFORE_SELECTION);

        registrationRepository.updateSelectionType(registrationId, SelectionType.SELECTION);
        Registration currentRegistration = registrationRepository.findRegistrationByRegistrationId(registrationId).get();
        assertThat(currentRegistration.selectionType()).isEqualTo(SelectionType.SELECTION);
    }

    @Test
    void 수강생_승인_처리() {
        int registrationId = 2;
        Registration registration = registrationRepository.findRegistrationByRegistrationId(registrationId).get();
        assertThat(registration.approvalType()).isEqualTo(ApprovalType.BEFORE_APPROVAL);

        registrationRepository.updateApprovalType(registrationId, ApprovalType.APPROVAL);
        Registration currentRegistration = registrationRepository.findRegistrationByRegistrationId(registrationId).get();
        assertThat(currentRegistration.approvalType()).isEqualTo(ApprovalType.APPROVAL);
    }

    @Test
    void 수강_취소_처리() {
        int registrationId = 2;
        assertThat(registrationRepository.findRegistrationByRegistrationId(registrationId).isPresent()).isTrue();

        registrationRepository.deleteRegistration(registrationId);
        assertThat(registrationRepository.findRegistrationByRegistrationId(registrationId).isEmpty()).isTrue();
    }

}

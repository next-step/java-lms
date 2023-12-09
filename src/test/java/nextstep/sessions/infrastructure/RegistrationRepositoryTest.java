package nextstep.sessions.infrastructure;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.data.registration.*;
import nextstep.sessions.domain.data.session.Session;
import nextstep.sessions.domain.exception.SessionsException;
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
        Session session = sessionRepository.findById(sessionId)
            .orElseThrow(() -> new SessionsException("강의 정보가 없습니다."));
        List<Registration> registrations = registrationRepository.findAllById(sessionId);

        Registration registration = new Registration(session, NsUserTest.SANJIGI, new Payment(1L, 2L, 3L, 800000L));
        registrationRepository.save(registration);
        List<Registration> currentRegistrations = registrationRepository.findAllById(sessionId);
        assertThat(currentRegistrations).hasSize(registrations.size() + 1);
        assertThat(currentRegistrations.stream()
            .map(Registration::userId)
            .collect(Collectors.toList())
        ).contains(registration.userId());
    }

    @Test
    void 수강생_선발_처리() {
        int registrationId = 13;
        Registration registration = registration(registrationId);
        assertThat(registration.selectionType()).isEqualTo(SelectionType.BEFORE_SELECTION);

        registration.select();
        registrationRepository.updateSelectionType(registration);
        Registration currentRegistration = registration(registrationId);
        assertThat(currentRegistration.selectionType()).isEqualTo(SelectionType.SELECTION);
    }

    @Test
    void 수강생_승인_처리() {
        int registrationId = 12;
        Registration registration = registration(registrationId);
        assertThat(registration.approvalType()).isEqualTo(ApprovalType.BEFORE_APPROVAL);

        registration.approve();
        registrationRepository.updateApprovalType(registration);
        Registration currentRegistration = registration(registrationId);
        assertThat(currentRegistration.approvalType()).isEqualTo(ApprovalType.APPROVAL);
    }

    @Test
    void 수강_취소_처리() {
        int registrationId = 13;
        Registration registration = registration(registrationId);
        registration.cancel();

        registrationRepository.deleteById(registrationId);
        assertThat(registrationRepository.findById(registrationId)).isEmpty();
    }

    private Registration registration(int registrationId) {
        return registrationRepository.findById(registrationId)
            .orElseThrow(() -> new SessionsException("등록된 수강 정보가 없습니다."));
    }
}

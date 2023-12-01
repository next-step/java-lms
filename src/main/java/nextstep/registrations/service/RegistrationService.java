package nextstep.registrations.service;

import nextstep.payments.domain.Payment;
import nextstep.registrations.domain.data.Registration;
import nextstep.registrations.domain.repository.RegistrationRepository;
import nextstep.sessions.domain.data.Session;
import nextstep.users.domain.NsUser;

public class RegistrationService {

    private final RegistrationRepository registrationRepository;

    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    public void enroll(long sessionId, NsUser loginUser, Payment payment) {
        Session session = registrationRepository.findBySessionId(sessionId);
        session.validateEnrollment();
        registrationRepository.save(new Registration(session, loginUser, payment));
    }
}

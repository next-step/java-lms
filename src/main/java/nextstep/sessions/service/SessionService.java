package nextstep.sessions.service;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.data.Session;
import nextstep.sessions.domain.data.vo.Registration;
import nextstep.sessions.repository.RegistrationRepository;
import nextstep.sessions.repository.SessionRepository;
import nextstep.users.domain.NsUser;

public class SessionService {

    private final SessionRepository sessionRepository;
    private final RegistrationRepository registrationRepository;

    public SessionService(SessionRepository sessionRepository, RegistrationRepository registrationRepository) {
        this.sessionRepository = sessionRepository;
        this.registrationRepository = registrationRepository;
    }

    public void enroll(int sessionId, NsUser loginUser, Payment payment) {
        Session session = sessionRepository.findSessionBySessionId(sessionId);
        Registration registration = session.registration(loginUser, payment);
        registrationRepository.saveRegistration(sessionId, registration);
    }
}

package nextstep.courses.service;

import nextstep.courses.domain.registration.Registration;
import nextstep.courses.domain.registration.RegistrationRepository;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.payments.domain.Payment;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final SessionRepository sessionRepository;
    private final RegistrationRepository registrationRepository;

    public RegistrationService(SessionRepository sessionRepository,
                               RegistrationRepository registrationRepository) {
        this.sessionRepository = sessionRepository;
        this.registrationRepository = registrationRepository;
    }

    public void register(Payment payment) {
        Session session = sessionRepository.findById(payment.getSessionId());
        session.validateEnroll(payment.getAmount());

        Registration registration = new Registration(payment.getSessionId(), payment.getNsUserId());
        registrationRepository.save(registration);
    }
}

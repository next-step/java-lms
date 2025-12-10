package nextstep.courses.service;

import java.util.List;
import nextstep.courses.domain.registration.Registration;
import nextstep.courses.domain.registration.RegistrationRepository;
import nextstep.courses.domain.registration.Registrations;
import nextstep.courses.domain.session.Enrollment;
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
        List<Registration> registered = registrationRepository.findBySessionId(session.getId());

        Enrollment enrollment = session.enrollment(registered);
        Registration registration = enrollment.enroll(payment.getAmount(), payment.getNsUserId());

        registrationRepository.save(registration);
    }
}
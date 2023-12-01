package nextstep.registrations.service;

import nextstep.payments.domain.Payment;
import nextstep.registrations.domain.data.Registration;
import nextstep.registrations.domain.data.Registrations;
import nextstep.registrations.domain.repository.RegistrationRepository;
import nextstep.users.domain.NsUser;

public class RegistrationService {

    private final RegistrationRepository registrationRepository;

    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    public void register(long sessionId, NsUser loginUser, Payment payment) {
        Registrations registrations = registrationRepository.findAllBySessionId(sessionId);
        registrations.validateSession();
        Registration registration = registrations.newRegistration(loginUser, payment);
        registrationRepository.save(registration);
    }
}

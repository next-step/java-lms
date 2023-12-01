package nextstep.registrations.domain.repository;

import nextstep.registrations.domain.data.Registration;
import nextstep.registrations.domain.data.Registrations;

public interface RegistrationRepository {

    Registrations findAllBySessionId(long sessionId);

    void save(Registration register);
}

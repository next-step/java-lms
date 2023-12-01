package nextstep.registrations.domain.repository;

import nextstep.registrations.domain.data.Registration;
import nextstep.sessions.domain.data.Session;

public interface RegistrationRepository {

    Session findBySessionId(long sessionId);

    void save(Registration register);
}

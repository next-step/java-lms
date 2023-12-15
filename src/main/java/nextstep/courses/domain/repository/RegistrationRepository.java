package nextstep.courses.domain.repository;

import nextstep.courses.domain.Registration;

import java.util.List;

public interface RegistrationRepository {
    int save(Registration registration);

    List<Registration> findAllBySessionId(Long sessionId);
}

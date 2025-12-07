package nextstep.courses.domain.registration;

import java.util.List;

public interface RegistrationRepository {
    int save(Registration registration);

    Registration findById(Long id);

    List<Registration> findBySessionId(Long sessionId);

    int countBySessionId(Long sessionId);
}

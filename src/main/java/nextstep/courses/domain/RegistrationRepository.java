package nextstep.courses.domain;

import java.util.List;

public interface RegistrationRepository {
    List<Registration> findBySessionId(Long sessionId);
}

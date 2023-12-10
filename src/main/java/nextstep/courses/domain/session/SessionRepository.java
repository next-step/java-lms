package nextstep.courses.domain.session;

import java.util.Optional;

public interface SessionRepository {

    Optional<EnrollmentSession> findBySessionId(Long sessionId);
}

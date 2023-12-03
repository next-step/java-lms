package nextstep.courses.domain;

import java.util.Optional;

public interface SessionRepository {

    Optional<Session> findBySessionId(Long sessionId);
}

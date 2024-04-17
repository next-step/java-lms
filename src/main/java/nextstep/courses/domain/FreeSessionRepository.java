package nextstep.courses.domain;

import java.util.Optional;

public interface FreeSessionRepository {
    Sessions findByCourseId(Long courseId);

    Optional<FreeSession> findBySessionId(Long sessionId);

    void saveSession(FreeSession session, Long courseId);
}

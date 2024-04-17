package nextstep.courses.domain;

import java.util.Optional;

public interface PaySessionRepository {
    Sessions findByCourseId(Long courseId);

    Optional<PaySession> findBySessionId(Long sessionId);

    void saveSession(PaySession session, Long courseId);
}

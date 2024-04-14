package nextstep.courses.domain;

import java.util.Optional;

public interface SessionRepository {
    Sessions findByCourseId(Long courseId);
    Optional<PaySession> findByPaySessionId(Long sessionId);
    Optional<FreeSession> findByFreeSessionId(Long sessionId);
    void savePaySession(PaySession paySession, Long courseId);
    void saveFreeSession(FreeSession freeSession, Long courseId);
}

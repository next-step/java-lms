package nextstep.courses.domain;

import java.util.Optional;

public interface SessionRepository {
    Sessions findByCourseId(Long courseId);
    <T> Optional<Session> findBySessionId(Long sessionId, Class<T> type);
    void saveSession(Session session, Long courseId);
}

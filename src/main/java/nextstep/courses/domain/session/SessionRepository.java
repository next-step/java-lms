package nextstep.courses.domain.session;

import java.util.Optional;

public interface SessionRepository {
    int save(Long courseId, Session session);
    Optional<Session> findById(Long id);
}

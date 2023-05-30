package nextstep.courses.domain;

import java.util.List;
import java.util.Optional;

public interface SessionRepository {
    Session save(Session session);

    Optional<Session> findById(SessionId sessionId);

    List<Session> findAll();

    void deleteAll();
}

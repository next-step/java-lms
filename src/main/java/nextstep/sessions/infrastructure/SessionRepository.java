package nextstep.sessions.infrastructure;

import nextstep.sessions.domain.Session;

import java.util.Optional;

public interface SessionRepository {
    Optional<Session> findById(long id);
    int save(Session session);
}

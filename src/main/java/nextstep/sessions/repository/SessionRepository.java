package nextstep.sessions.repository;

import java.util.Optional;

import nextstep.sessions.domain.data.Session;

public interface SessionRepository {

    int saveSession(Session session);

    Optional<Session> findSessionBySessionId(int sessionId);

}

package nextstep.sessions.repository;

import nextstep.sessions.domain.data.Session;

public interface SessionRepository {

    int saveSession(Session session);

    Session findSessionBySessionId(int sessionId);

}

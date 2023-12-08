package nextstep.sessions.repository;

import java.util.Optional;

import nextstep.sessions.domain.data.Session;

public interface SessionRepository {

    int save(Session session);

    Optional<Session> findById(int sessionId);

}
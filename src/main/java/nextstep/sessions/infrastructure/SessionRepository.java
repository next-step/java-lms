package nextstep.sessions.infrastructure;

import nextstep.sessions.domain.Session;

public interface SessionRepository {

    long save(Session session);

    Session findById(Long id);

    int enroll(Session session);
}

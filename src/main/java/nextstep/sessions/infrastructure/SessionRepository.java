package nextstep.sessions.infrastructure;

import nextstep.sessions.domain.Session;

public interface SessionRepository {

    int save(Session session);

    Session findByName(String name);

    int update(Session session);
}

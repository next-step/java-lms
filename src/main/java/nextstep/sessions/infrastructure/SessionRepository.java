package nextstep.sessions.infrastructure;

import nextstep.sessions.domain.Session;

public interface SessionRepository {

    long save(Session session);

    Session findByName(String name);

    int updateStudentCount(Session session);
}

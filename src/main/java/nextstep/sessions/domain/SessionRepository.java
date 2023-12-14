package nextstep.sessions.domain;

import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionStudent;

public interface SessionRepository {

    Long save(Session session);

    Session findById(Long id);

    Long enroll(Session session, SessionStudent student);
}

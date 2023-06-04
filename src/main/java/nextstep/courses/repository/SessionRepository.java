package nextstep.courses.repository;

import nextstep.courses.domain.session.Session;
import nextstep.users.domain.NsUser;

public interface SessionRepository {
    Session create(Session newSession);

    Session findById(Long sessionId);

    Session register(Session session, NsUser nsUser);
}

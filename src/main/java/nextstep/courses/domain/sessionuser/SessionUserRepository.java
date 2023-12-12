package nextstep.courses.domain.sessionuser;

import nextstep.courses.domain.session.Session;

public interface SessionUserRepository {

    SessionUsers findBySession(Session session);

    void save(SessionUser sessionUser);

    void cancel(SessionUser sessionUser);
}

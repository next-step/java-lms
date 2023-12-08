package nextstep.courses.domain.sessionuser;

import nextstep.courses.domain.session.Session;

public interface SessionUsersRepository {

    SessionUsers findBySession(Session session);

    void save(SessionUser sessionUser);
}

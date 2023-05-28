package nextstep.courses.domain;


import nextstep.users.domain.User;

import java.util.List;

public interface SessionRepository {
    Session findBySessionId(long id);

    long save(Session session, long courseId);

    long enrollUser(Session session);

    List<User> findUsersBySessionId(long sessionId);
}

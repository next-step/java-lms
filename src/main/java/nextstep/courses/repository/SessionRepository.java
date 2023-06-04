package nextstep.courses.repository;

import nextstep.courses.domain.session.Session;
import nextstep.users.domain.NsUser;

import java.util.List;

public interface SessionRepository {
    int create(Session newSession);

    List<Session> findByCourseId(Long courseId);

    Session findById(Long sessionId);

    int register(Session session, NsUser nsUser);
}

package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.List;

public interface SessionRepository {
    long save(Session session, Long courseId);

    Session findById(Long sessionId);

    List<Session> findByCourseId(Long courseId);

    long saveSessionUser(Session session, NsUser nextStepUser);

    List<NsUser> findAllUserBySessionId(Long sessionId);
}

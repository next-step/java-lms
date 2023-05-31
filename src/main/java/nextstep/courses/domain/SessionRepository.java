package nextstep.courses.domain;

import java.util.List;

public interface SessionRepository {
    Session save(Session session, Long courseId);

    Session findById(Long sessionId);

    List<Session> findByCourseId(Long courseId);

    long saveSessionUser(Session session, SessionUser sessionUser);

    List<SessionUser> findAllUserBySessionId(Long sessionId);

    SessionUser findUserByUserIdAndSessionId(Long sessionId, Long userId);

    void updateSessionUserStatus(Long sessionId, SessionUser sessionUser);
}

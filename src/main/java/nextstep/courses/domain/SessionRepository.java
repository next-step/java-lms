package nextstep.courses.domain;

import nextstep.users.domain.NextStepUser;

import java.util.List;

public interface SessionRepository {

  Session save(Session session, Long courseId);

  Session findById(Long sessionId);

  List<Session> findByCourseId(Long courseId);

  SessionUser saveSessionUser(SessionUser sessionUser);

  List<SessionUser> findAllSessionUserBySessionId(Long sessionId);

  void updateSessionUserStatus(SessionUser sessionUser);

  SessionUser findBySessionIdAndUserId(Long sessionId, Long userId);
}

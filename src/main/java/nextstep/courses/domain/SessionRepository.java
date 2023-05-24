package nextstep.courses.domain;

import java.util.List;

public interface SessionRepository {

  Session save(Session session, Long courseId);

  Session findById(Long sessionId);

  List<Session> findByCourseId(Long courseId);

  void saveAllSessionUser(Session session);

  List<SessionUser> findAllSessionUserBySessionId(Long sessionId);
}

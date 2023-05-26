package nextstep.courses.domain;

import nextstep.users.domain.NextStepUser;

import java.util.List;

public interface SessionRepository {

  Session save(Session session, Long courseId);

  Session findById(Long sessionId);

  List<Session> findByCourseId(Long courseId);

  void saveSessionUser(Session session, NextStepUser nextStepUser);

  List<SessionUser> findAllSessionUserBySessionId(Long sessionId);
}

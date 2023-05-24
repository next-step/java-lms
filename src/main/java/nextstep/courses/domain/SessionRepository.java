package nextstep.courses.domain;

public interface SessionRepository {

  Session save(Session session, Long courseId);

  Session findById(Long sessionId);
}

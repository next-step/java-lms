package nextstep.sessions.domain;

import java.util.List;
import java.util.Optional;

public interface SessionRepository {
  Session save(Session session, Long courseId);
  Optional<Session> findById(Long id);
  List<Session> findAllByCourseId(Long courseId);
  void deleteById(Long id);
}

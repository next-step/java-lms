package nextstep.courses.domain;

import java.util.List;
import java.util.Optional;

public interface SessionRepository {
    int save(Session session);

    Session findById(Long id);
    List<Session> findByCourseId(Long id);

    int update(Session session);

    int delete(Long id);
    Optional<List<Session>> findBySessionIds(List<Long> sessionIds);
}

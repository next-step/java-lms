package nextstep.sessions.infrastructore;

import nextstep.sessions.domain.Session;

import java.util.List;
import java.util.Optional;

public interface SessionRepository {

    long save(Session session);

    void saveAll(List<Session> sessions);

    Optional<Session> findById(long sessionId);

    List<Session> findByIds(List<Long> ids);

    List<Session> findByCourseId(long courseId);

    int update(Session session);
}

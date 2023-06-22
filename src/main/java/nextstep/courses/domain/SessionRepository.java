package nextstep.courses.domain;

import java.util.List;

public interface SessionRepository {
    int save(Session session);

    Session findById(Long id);

    int update(Session session);

    int delete(Long id);
    List<Session> findBySessionIds(List<Long> sessionIds);
}

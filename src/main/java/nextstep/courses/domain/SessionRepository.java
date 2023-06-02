package nextstep.courses.domain;

import java.util.List;

public interface SessionRepository {
    int save(Long courseId, Session session);

    Session findById(int id);

    List<Session> findByCourseId(Long courseId);
}

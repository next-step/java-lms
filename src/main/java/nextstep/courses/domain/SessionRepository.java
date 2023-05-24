package nextstep.courses.domain;

import java.util.List;

public interface SessionRepository {
    int save(Session session, Long courseId);

    Session findById(Long id);

    List<Session> findByCourseId(Long courseId);
}

package nextstep.courses.infrastructure;

import nextstep.courses.domain.Session;

import java.util.List;

public interface SessionRepository {
    int save(Session session);

    Session findById(Long id);

    List<Session> findByCourseId(Long courseId);
}

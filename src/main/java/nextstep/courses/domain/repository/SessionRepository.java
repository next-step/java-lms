package nextstep.courses.domain.repository;

import nextstep.courses.domain.Session;

import java.util.List;

public interface SessionRepository {
    long save(Session session);

    Session findById(Long id);

    int update(Session session);

    int delete(Long id);

    List<Session> findSessionsByCourseId(Long courseId);
}

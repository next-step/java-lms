package nextstep.courses.repository;

import nextstep.courses.domain.session.Session;

import java.util.List;

public interface SessionRepository {
    int save(Session session);

    Session findById(Long id);

    List<Session> findByCourseId(Long courseId);
}

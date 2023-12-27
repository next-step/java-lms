package nextstep.courses.domain.course.session;

import java.util.Optional;

public interface SessionRepository {
    Optional<Session> findById(Long id);

    Session save(Long courseId, Session session);

    int update(Long sessionId, Session session);

    Sessions findAllByCourseId(Long courseId);

    int updateCourseId(Long courseId, Session session);
}

package nextstep.courses.domain;

import java.util.Optional;

public interface SessionRepository {
    int save(Session session);
    Session findById(Long id);
    Optional<Sessions> findByCourseId(Long courseId);
}

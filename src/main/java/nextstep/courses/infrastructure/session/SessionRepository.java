package nextstep.courses.infrastructure.session;

import java.util.List;
import java.util.Optional;

public interface SessionRepository {

    Long save(final SessionEntity sessionEntity);

    void update(final SessionEntity sessionEntity);

    Optional<SessionEntity> findById(final Long id);

    List<SessionEntity> findAllByCourseId(final Long courseId);
}

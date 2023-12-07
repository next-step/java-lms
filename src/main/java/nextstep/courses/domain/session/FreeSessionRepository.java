package nextstep.courses.domain.session;

import java.util.Optional;

public interface FreeSessionRepository {
    int save(Long courseId, FreeSession session);

    Optional<FreeSession> findById(Long id);
}

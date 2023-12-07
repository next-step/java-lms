package nextstep.courses.domain.session;

import java.util.Optional;

public interface PaidSessionRepository {
    int save(Long courseId, PaidSession session);
    Optional<PaidSession> findById(Long id);
}

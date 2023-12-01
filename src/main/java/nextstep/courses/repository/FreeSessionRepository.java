package nextstep.courses.repository;

import nextstep.courses.domain.FreeSession;

public interface FreeSessionRepository {
    int save(FreeSession session, Long courseId);

    FreeSession findById(Long id);

    boolean existsById(Long id);
}


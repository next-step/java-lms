package nextstep.courses.repository;

import nextstep.courses.domain.ChargedSession;

public interface ChargedSessionRepository {
    int save(ChargedSession session, Long courseId);

    ChargedSession findById(Long id);

    boolean existsById(Long id);
}


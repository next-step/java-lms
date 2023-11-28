package nextstep.courses.repository;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.FreeSession;

public interface FreeSessionRepository {
    int save(Long id, FreeSession session, Course course);

    FreeSession findById(Long id);

    boolean existsById(Long id);
}


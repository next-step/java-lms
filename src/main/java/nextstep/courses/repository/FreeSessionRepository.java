package nextstep.courses.repository;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.FreeSession;

public interface FreeSessionRepository {
    int save(Course course, FreeSession session);

    FreeSession findById(Long id);
}

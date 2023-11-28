package nextstep.courses.repository;

import nextstep.courses.domain.ChargedSession;
import nextstep.courses.domain.Course;

public interface ChargedSessionRepository {
    int save(Long id, ChargedSession session, Course course);

    ChargedSession findById(Long id);

    boolean existsById(Long id);
}


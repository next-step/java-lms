package nextstep.courses.repository;

import nextstep.courses.domain.ChargedSession;
import nextstep.courses.domain.Course;

public interface ChargedSessionRepository {
    int save(ChargedSession session, Course course);

    ChargedSession findById(Long id);
}


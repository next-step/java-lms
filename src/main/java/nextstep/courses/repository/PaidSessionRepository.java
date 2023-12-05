package nextstep.courses.repository;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.PaidSession;

public interface PaidSessionRepository {
    int save(Course course, PaidSession session);

    PaidSession findById(Long id);
}

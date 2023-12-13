package nextstep.courses.repository;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.PaidSession;

import java.util.List;

public interface PaidSessionRepository {
    int save(Course course, PaidSession session);

    PaidSession findById(Long id);

    List<PaidSession> findByCourseId(Long courseId);
}

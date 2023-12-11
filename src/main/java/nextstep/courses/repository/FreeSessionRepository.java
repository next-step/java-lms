package nextstep.courses.repository;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.FreeSession;

import java.util.List;

public interface FreeSessionRepository {
    int save(Course course, FreeSession session);

    FreeSession findById(Long id);

    List<FreeSession> findByCourseId(Long courseId);
}

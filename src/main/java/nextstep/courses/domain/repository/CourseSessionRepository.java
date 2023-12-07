package nextstep.courses.domain.repository;

import java.util.List;

public interface CourseSessionRepository {
    int save(CourseSession courseSession);

    List<Integer> findByCourseId(Long courseId);

    int findBySessionId(Long sessionId);
}

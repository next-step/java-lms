package nextstep.courses.domain;

import java.util.List;

public interface SessionRepository {

    int save(String session);

    Session findById(Long id);

    List<Session> findByCourseId(Long courseId);
}

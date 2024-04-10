package nextstep.courses.domain.session;

import java.util.List;

public interface SessionRepository {
    List<Session> findByCourseId(Long id);

    Session findById(Long id);
}

package nextstep.courses.domain;

import java.util.List;
import nextstep.courses.domain.session.Session;

public interface SessionRepository {

    Long save(Session session);

    Session findById(Long id);

    List<Session> findByCourseId(Long courseId);

}

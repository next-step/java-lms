package nextstep.courses.repository;

import nextstep.courses.domain.Session;

public interface FreeSessionRepository {

    int save(Session session, Long courseId);

    Session findById(Long id);
}

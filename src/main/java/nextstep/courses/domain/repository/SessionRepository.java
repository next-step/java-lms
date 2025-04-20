package nextstep.courses.domain.repository;

import nextstep.courses.domain.model.Session;

public interface SessionRepository {
    int save(Session session);

    Session findById(Long id);
}

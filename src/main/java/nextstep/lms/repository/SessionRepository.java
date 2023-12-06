package nextstep.lms.repository;

import nextstep.lms.domain.Session;

public interface SessionRepository {
    int save(Session session);

    Session findById(Long id);
}

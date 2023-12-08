package nextstep.session.repository;

import nextstep.session.domain.Session;

public interface SessionRepository {

    void save(Session session);

    Session findById(Long id);
}

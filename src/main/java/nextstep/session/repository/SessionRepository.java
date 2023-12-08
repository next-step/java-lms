package nextstep.session.repository;

import nextstep.session.domain.Session;

public interface SessionRepository {

    Long save(Session session);

    Session findById(Long id);
}

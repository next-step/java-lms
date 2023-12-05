package nextstep.courses.domain.cource;

import nextstep.courses.domain.session.Session;

public interface SessionRepository {
    int save(Session session);

    Session findById(Long id);
}

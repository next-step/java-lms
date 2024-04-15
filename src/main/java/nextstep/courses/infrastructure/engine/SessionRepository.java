package nextstep.courses.infrastructure.engine;

import nextstep.courses.domain.Session;

public interface SessionRepository {

    int save(Session entity);

    Session findById(Long id);

}

package nextstep.courses.infrastructure.engine;

import nextstep.courses.domain.Session;

import java.util.Optional;

public interface SessionRepository {

    int save(Session entity);

    Optional<Session> findById(Long id);

}

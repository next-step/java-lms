package nextstep.courses.domain;

import java.util.Optional;

public interface SessionRepository {

    void save(Session session);

    Optional<Session> findById(Long id);
}

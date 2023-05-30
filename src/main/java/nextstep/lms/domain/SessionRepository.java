package nextstep.lms.domain;

import java.util.Optional;

public interface SessionRepository {
    Optional<Session> findById(Long id);
    void save(Session session);
}

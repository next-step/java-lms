package nextstep.courses.domain.session;

import java.util.Optional;

public interface SessionRepository {

    Optional<Session> findById(Long id);

    int save(Session session);
}

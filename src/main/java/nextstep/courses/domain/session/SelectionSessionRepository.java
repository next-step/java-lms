package nextstep.courses.domain.session;

import java.util.List;
import java.util.Optional;

public interface SelectionSessionRepository {
    int save(SelectionSession session);

    Optional<SelectionSession> findById(long id);

    Optional<List<SelectionSession>> findBySessionId(long sessionId);
}

package nextstep.sessions.infrastructure;

import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionRepository;
import org.springframework.stereotype.Repository;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    @Override
    public Session findById(Long id) {
        return null;
    }

}

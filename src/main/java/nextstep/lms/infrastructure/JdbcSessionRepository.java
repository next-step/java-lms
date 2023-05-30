package nextstep.lms.infrastructure;

import nextstep.lms.domain.Session;
import nextstep.lms.domain.SessionRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    @Override
    public Optional<Session> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Session session) {

    }
}

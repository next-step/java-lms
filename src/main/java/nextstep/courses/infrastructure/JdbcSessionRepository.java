package nextstep.courses.infrastructure;

import nextstep.courses.domain.course.session.Session;
import nextstep.courses.domain.course.session.SessionRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    @Override
    public Optional<Session> findById(Long id) {
        return Optional.empty();
    }
}

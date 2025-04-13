package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.Session;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("sessionRepository")
public interface SessionRepository {
    Optional<Session> findById(Long id);

    void update(Session session);
} 
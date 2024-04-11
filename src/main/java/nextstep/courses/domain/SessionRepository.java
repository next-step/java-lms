package nextstep.courses.domain;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository {
    Optional<Session> findById(Long sessionId);
}

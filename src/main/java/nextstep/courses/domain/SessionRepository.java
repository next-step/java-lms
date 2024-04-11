package nextstep.courses.domain;

import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository {
    Session findById(Long sessionId);
}

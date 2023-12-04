package nextstep.courses.infrastructure;

import nextstep.courses.domain.Session;
import org.springframework.stereotype.Repository;

@Repository("sessionRepository")
public interface SessionRepository {
    Session findById(long sessionId);
}

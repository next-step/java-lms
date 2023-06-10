package nextstep.courses.domain.session;

import java.util.Optional;
import nextstep.courses.domain.session.Session;

public interface SessionRepository {

  Optional<Session> findById(Long id);
}

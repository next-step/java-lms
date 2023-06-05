package nextstep.sessions.infrastructure;

import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionRepository;
import org.springframework.stereotype.Repository;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

  @Override
  public int save(Session session) {
    return 0;
  }

  @Override
  public Session findById(Long id) {
    return null;
  }

  @Override
  public void update(Session session) {

  }
}

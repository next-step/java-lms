package nextstep.session.domain;

import java.util.Optional;

public interface SessionRepository {
	int save(Session session);
	Optional<Session> findById(long id);
	int update(Session session);
	int deleteById(long id);

}

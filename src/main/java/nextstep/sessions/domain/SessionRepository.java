package nextstep.sessions.domain;

public interface SessionRepository {
	int save(final Session session);
	Session findById(Long id);
}

package nextstep.courses.domain.session;

public interface SessionRepository {
    Session save(Session session);

    Session findById(Long id);

    Session update(Session session);
}

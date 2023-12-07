package nextstep.courses.domain.session;

public interface SessionRepository {
    long save(Session session);

    Session findById(Long id);
}

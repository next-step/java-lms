package nextstep.courses.domain.Session;

public interface SessionRepository {
    int save(Session session);
    Session findById(Long id);
}

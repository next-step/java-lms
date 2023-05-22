package nextstep.courses.domain;

public interface SessionRepository {
    long save(Session session);

    Session findById(Long id);
}

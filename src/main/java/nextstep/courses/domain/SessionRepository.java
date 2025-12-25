package nextstep.courses.domain;

public interface SessionRepository {
    int save(Session session);

    Session findById(Long id);
}

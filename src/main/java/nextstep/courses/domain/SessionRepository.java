package nextstep.courses.domain;

public interface SessionRepository {

    Session findById(Long id);

    void save(Session session);
}

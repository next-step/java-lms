package nextstep.courses.domain;

public interface SessionRepository {

    Session save(Session session);
    Session findById(Long id);
}

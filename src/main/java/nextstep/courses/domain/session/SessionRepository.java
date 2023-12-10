package nextstep.courses.domain.session;

public interface SessionRepository {

    Session findById(Long id);

    void save(Session session);

}

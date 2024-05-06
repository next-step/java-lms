package nextstep.session.domain;

public interface SessionRepository {

    Session findById(Long id);

    int save(Session session);

    void clear();
}

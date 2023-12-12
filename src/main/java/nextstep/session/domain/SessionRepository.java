package nextstep.session.domain;

public interface SessionRepository {

    Long save(Session session);

    Session findById(Long id);

}

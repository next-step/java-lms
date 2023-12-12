package nextstep.courses.domain.session;

public interface SessionRepository {

    Session findById(Long id);

    Long save(Session session);

}

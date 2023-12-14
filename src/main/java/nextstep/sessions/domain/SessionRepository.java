package nextstep.sessions.domain;

public interface SessionRepository {

    Long save(Session session);

    Session findById(Long id);

    Long enroll(Session session, SessionStudent student);
}

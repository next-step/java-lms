package nextstep.sessions.domain;

public interface SessionRepository {

    Long save(Session session);

    Session findById(Long id);

    Long enroll(Session session, SessionStudent student);

    SessionStudent studentFindBySessionIdAndUserId(Long sessionId, Long userId);

    void approvalStudent(SessionStudent student);

    void cancelStudent(SessionStudent student);
}

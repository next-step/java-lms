package nextstep.lms.domain;

public interface SessionRepository {
    int save(Session session);

    void changeImage(Session session);

    Session findById(Long id);

    void changeSessionState(Session session);

    void updateRegisteredStudent(Session session);

    void changeSessionType(Session session);
}

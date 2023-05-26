package nextstep.lms.domain;

public interface SessionRepository {
    int save(Session session);

    void changeImage(Session session);

    Session findById(Long id);

    void changeSessionType(Session session);

    void registerStudent(Session session);
}

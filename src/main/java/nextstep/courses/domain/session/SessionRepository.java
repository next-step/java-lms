package nextstep.courses.domain.session;

public interface SessionRepository {
    int save(Session session);

    Session findById(Long sessionId);

    int updateNumberOfStudent(int numberOfStudent);
}

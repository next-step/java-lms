package nextstep.courses.domain.session;

public interface SessionRepository {
    void save(Long courseId, Session session);

    Sessions findByCourseId(Long courseId);

    Session findById(Long SessionId);
}

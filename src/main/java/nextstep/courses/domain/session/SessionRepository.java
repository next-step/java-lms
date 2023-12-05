package nextstep.courses.domain.session;

public interface SessionRepository {
    int save(Session session, Long courseId);

    Session findById(Long id);
}

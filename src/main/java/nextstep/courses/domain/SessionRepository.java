package nextstep.courses.domain;

public interface SessionRepository {
    Session findById(Long id);

    long save(Session session, Long courseId);
}

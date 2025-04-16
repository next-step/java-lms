package nextstep.courses.domain;

public interface SessionRepository {
    int save(Session session, Long courseId);

    Session findById(Long id);
}

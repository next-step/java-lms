package nextstep.courses.domain.session;

public interface SessionsRepository {
    int save(long courseId, Sessions sessions);

    Sessions findByCourseId(Long courseId);
}

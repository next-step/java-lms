package nextstep.courses.domain;

public interface SessionRepository {

    int save(Course course);

    Session findById(Long id);
}

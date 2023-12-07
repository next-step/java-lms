package nextstep.courses.domain;

public interface SessionEnrollRepository {
    int save(SessionEnroll sessionEnroll);

    SessionEnroll findById(Long id);
}

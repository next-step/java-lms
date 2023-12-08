package nextstep.courses.domain;

public interface SessionEnrollRepository {
    Long save(SessionEnroll sessionEnroll);

    SessionEnroll findById(Long id);
}

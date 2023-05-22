package nextstep.courses.domain.repository;

import nextstep.courses.domain.SessionTime;

public interface SessionTimeRepository {
    long save(SessionTime session);
    SessionTime findById(Long id);
    int update(SessionTime session);
    int delete(Long id);
}

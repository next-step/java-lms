package nextstep.courses.repository;

import nextstep.courses.domain.Session;

public interface SessionRepository {
    int save(long courseId, long imageId, Session session);

    Session findById(Long id);
}

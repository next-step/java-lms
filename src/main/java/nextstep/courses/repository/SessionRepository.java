package nextstep.courses.repository;

import nextstep.courses.domain.session.Session;

import java.util.List;

public interface SessionRepository {
    int create(Session newSession);

    List<Session> findByCourseId(Long courseId);

    Session findById(Long sessionId);
}

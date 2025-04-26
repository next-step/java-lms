package nextstep.session.domain;

import nextstep.courses.domain.Course;

public interface SessionRepository {
    int save(Session session);

    Session findById(Long id);
}

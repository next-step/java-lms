package nextstep.courses.app;

import nextstep.courses.domain.Session;

public interface SessionService {
    long save(Session session);

    Session findById(long id);
}

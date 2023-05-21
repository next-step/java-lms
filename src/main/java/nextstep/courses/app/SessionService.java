package nextstep.courses.app;

import nextstep.courses.domain.Session;

import java.util.List;

public interface SessionService {
    long save(Session session);

    Session findById(long id);

    long register(long sessionId, List<String> userIds);
}

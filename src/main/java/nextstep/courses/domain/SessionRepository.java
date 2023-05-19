package nextstep.courses.domain;

import java.util.List;

public interface SessionRepository {
    int save(Session session);

    Session findById(Long id);

    int saveSessionJoin(Session session);

    List<SessionJoin> findAllSessionJoinBySessionId(Long sessionId);
}

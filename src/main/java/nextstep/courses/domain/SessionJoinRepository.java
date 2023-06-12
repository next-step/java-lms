package nextstep.courses.domain;

import java.util.List;

public interface SessionJoinRepository {
    int save(Session session);

    List<SessionJoin> findAllBySessionId(Long sessionId);
}
